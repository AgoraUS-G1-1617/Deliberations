package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.CensusUser;
import domain.Comment;
import domain.Thread;
import domain.User;
import security.UserAccount;
import services.CommentService;
import services.KarmaService;
import services.RankService;
import services.ThreadService;
import services.UserService;

@Controller
@RequestMapping("/thread")
public class ThreadController extends AbstractController {

	// Supporting services ----------------------------------------------------

	@Autowired
	private ThreadService threadService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private KarmaService karmaService;

	// Constructors -----------------------------------------------------------

	public ThreadController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping("/list")
	public ModelAndView messagesReceived(@RequestParam int page) {
		ModelAndView result;
		Page<Thread> items;
		Pageable pageable;
		pageable = new PageRequest(page - 1, 5);

		items = threadService.findAll(pageable);

		result = new ModelAndView("thread/list");
		result.addObject("threads", items.getContent());
		result.addObject("p", page);
		result.addObject("total_pages", items.getTotalPages());
		result.addObject("actUserId",userService.findOneByPrincipal().getId());

		return result;
	}
	// Displaying -------------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView seeThread(@RequestParam int id, @RequestParam Integer p) {
		ModelAndView result;
		domain.Thread hilo;

		hilo = threadService.findOne(id);

		Comment comment = new Comment();

		comment.setCreationMoment(new Date());
		comment.setThread(hilo);
		comment.setUser(userService.findOneByPrincipal());

		result = createListModelAndView(id, p, comment);
		
		return result;

	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public ModelAndView saveComment(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;
		int page;

		page = threadService.calculateLastPage(comment, null);

		if (binding.hasErrors()) {

			result = createListModelAndView(comment.getThread().getId(), page, comment);

		} else {
			if(comment.getText().length()>65535){

				result = createListModelAndView(comment.getThread().getId(), page, comment);
				result.addObject("commentLengthError", "comment.length.error");
				
			}else{
				
				result = new ModelAndView("redirect:display.do?id=" + comment.getThread().getId() + "&p=" + page);
				
				try {
					commentService.save(comment);
				} catch (Throwable op) {
					op.printStackTrace();
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		domain.Thread thread;

		thread = threadService.create();
		result = new ModelAndView("thread/edit");

		result.addObject("thread", thread);

		result.addObject("actionURI", "thread/edit.do");

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int threadId) {
		domain.Thread thread;
		ModelAndView result;
		
		thread = threadService.findOne(threadId);

		if(thread.getUser().equals(userService.findOneByPrincipal())){
			result = createEditModelAndView(thread);
		}else{
			result = messagesReceived(1);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid domain.Thread thread, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(thread);
		} else {
			try {
				if(thread.getTitle().length()>255 || thread.getDecription().length()>65535){
					Map<String, String> lengthErrors;
					lengthErrors = new HashMap<String, String>();
					if(thread.getTitle().length()>255){
						lengthErrors.put("titleLengthError", "title.length.error");
					}
					if(thread.getDecription().length()>65535){
						lengthErrors.put("descriptionLengthError", "comment.length.error");
					}
					result = new ModelAndView("thread/edit");
					result.addObject("thread", thread);
					result.addAllObjects(lengthErrors);
				}else{
					threadService.save(thread);
					result = new ModelAndView("redirect:list.do?page=1");
				}
			} catch (Throwable oops) {
				result = createEditModelAndView(thread, "commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/open", method = RequestMethod.GET)
	public ModelAndView open(@RequestParam int threadId) {
		ModelAndView result;
		
		try {
			Thread thread;
			
			thread = threadService.findOne(threadId);
			threadService.open(thread);
			
			result = new ModelAndView("redirect:list.do?page=1");
		} catch (Throwable oops) {
			result = seeThread(threadId,1);
			result.addObject("messageError","commit.error");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.GET)
	public ModelAndView close(@RequestParam int threadId) {
			ModelAndView result;
			
			try {
				Thread thread;
				
				thread = threadService.findOne(threadId);
				threadService.close(thread);
				
				result = new ModelAndView("redirect:list.do?page=1");
			} catch (Throwable oops) {
				result = seeThread(threadId,1);
				result.addObject("messageError","commit.error");
			}
			
			return result;
	}

	// login from census, this make a http get to census module and get the json
	// output, after tries to make a login with json output
	// if the person is no present in the bd, save the new person and log in the
	// context.
	// we are to trust the username census give us is unique
	// if the person is present in the bd, log in the context
	// TODO hacerlo bien, nueva funcionalidad
	@RequestMapping("/loginFromCensus")
	public ModelAndView loginFromCensus(String username, HttpServletRequest httpRequest)
			throws JsonParseException, JsonMappingException, IOException {
		ModelAndView result;
		// Encontrar en Censos con JSon

		ObjectMapper objectMapper = new ObjectMapper();

		// Document
		// doc=Jsoup.connect("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username).get();
		
		// Si da error, el usuario no est� en el censo

		CensusUser censusUser = null;
		String nameFinal = "";
		try {

			try {
				censusUser = objectMapper.readValue(
						new URL("http://localhost:8080/ADMCensus/census/findCensusByVote.do?idVotacion=" + 1),
						CensusUser.class);
			} catch (JsonParseException e) {
				return loginFromCensusFrom();
			}
			Assert.isTrue(censusUser.getUsername() != null);

			for (String name : censusUser.getVoto_por_usuario().keySet()) {

				if (name.equals(username)) {

					nameFinal = name;
				}

			}

		} catch (Exception e) {

			return loginFromCensusFrom();
		}

		// Si no, adelante

		if (nameFinal.equals("")) {

			return loginFromCensusFrom();
		} else if (userService.findByUsername(nameFinal) != null) {// esta en la
																	// base de
																	// datos

			// Login

			loginMakeFromCensus(userService.findByUsername(username).getUserAccount(), httpRequest);

			result = new ModelAndView("list");

		} else { // Al no estar, se le registra
			User user;
			UserAccount userAccount;

			user = userService.create(username);
			userAccount = user.getUserAccount();

			userService.save(user);
			loginMakeFromCensus(userAccount, httpRequest);

			result = new ModelAndView("list");
		}
		return result;
	}

	@RequestMapping(value = "loginFromCensusForm", method = RequestMethod.GET)
	public ModelAndView loginFromCensusFrom() {
		ModelAndView result;

		result = new ModelAndView("user/loginFromCensusForm");

		return result;
	}

	public void loginMakeFromCensus(UserAccount user, HttpServletRequest request) {

		try {
			// Must be called from request filtered by Spring Security,
			// otherwise SecurityContextHolder is not updated
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword(), null);
			token.setDetails(new WebAuthenticationDetails(request));
			DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
			authenticator.setUserDetailsService(userDetailsService);

			Authentication authentication = authenticator.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			e.printStackTrace();
			SecurityContextHolder.getContext().setAuthentication(null);
		}
	}

	// Ancillary methods
	// ----------------------------------------------------------------------

	private ModelAndView createEditModelAndView(domain.Thread thread) {
		ModelAndView result;

		result = createEditModelAndView(thread, null);

		return result;
	}

	public ModelAndView createEditModelAndView(domain.Thread thread, String message) {
		ModelAndView result;

		result = new ModelAndView("thread/edit");

		result.addObject("thread", thread);
		result.addObject("messageError", message);

		return result;
	}

	private ModelAndView createListModelAndView(int id, int p, Comment c){
		ModelAndView result;
		domain.Thread hilo;
		Collection<Comment> comments;
		Integer lastPage;
		HashMap<Integer,List<Integer>> commentsKarma;
		List<Integer> karmasOfUserAtThread;
		Integer loggedUserId;

		hilo = threadService.findOne(id);
		comments = commentService.findCommentsByPage(id, p);
		lastPage = threadService.calculateLastPage(null, hilo);
		commentsKarma = karmaService.karmaOfThread(id, p);
		loggedUserId = userService.findOneByPrincipal().getId();
		karmasOfUserAtThread = karmaService.karmasOfUserAtThread(id, p, userService.findOneByPrincipal().getId());

		result = new ModelAndView("thread/display");
		result.addObject("hilo", hilo);
		result.addObject("comments", comments);
		result.addObject("commentsKarma",commentsKarma);
		result.addObject("comment", c);
		result.addObject("p", p);
		result.addObject("lastPage", lastPage);
		result.addObject("rankService", rankService);
		result.addObject("loggedUserId", loggedUserId);
		result.addObject("karmasOfUserAtThread", karmasOfUserAtThread);

		return result;
	}

	// CREACI�N LOGIN FROM CABINA DE VOTACI�N, NOS VIENE UNA ID Y UN TOKEN PARA
	// COMPRAR CON AUTENTIFICACI�N IMPLEMENTAR ES NECESARIO IMPLEMENTAR -

	// CONEXION CON AUTENTICICAION A TRAVES DE JSON PARA PODER LOGUEAR DESDE EL
	// CABINA DE VOTACI�N

	// CREACI�N DE HILOS DESDE CREACI�N/ADMINISTRACI�N DE VOTACIONES, LES
	// DEBEMOS DE DAR UN LINK PARA QUE NOS TRAIGA Y CREEMOS UNOS NOSOTROS

	@RequestMapping("/createThreadFromVotacion")
	public ModelAndView createTreadFromVotacion(String name) {

		User user = userService.findByUsername("customer");

		domain.Thread nuevo = new domain.Thread();
		nuevo.setCreationMoment(new Date());
		nuevo.setDecription("Hilo sobre la votaci�n: " + name);
		nuevo.setUser(user);
		nuevo.setTitle("Votaci�n " + name);
		nuevo.setComments(new ArrayList<Comment>());

		threadService.save(nuevo);
		return new ModelAndView("redirect:list.do?page=1");

		// CreacionAdminVotaciones/#/create
	}

}
