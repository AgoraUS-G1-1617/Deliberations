/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import domain.User;
import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.ThreadService;
import services.UserService;


@Controller
@RequestMapping("/thread")
public class ThreadController extends AbstractController {
	
	// Supporting services -----------------------
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// Constructors -----------------------------------------------------------

	public ThreadController() {
		super();
	}

	// Listing ------------------------------------------------------------------
	
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public ModelAndView prueba(){
		//creacion de variables
		ModelAndView result;
		Collection<domain.Thread> threads;
		//asignacion de valores
		threads=threadService.findAll();
		result=new ModelAndView("thread/list");
		result.addObject("threads",threads);
		result.addObject("allThreads",threadService.findAll());
		//mostrar resultado
		return result;
	}
	
	// Displaying ---------------------------------------------------------
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView seeThread(@RequestParam int id, @RequestParam Integer p){
		ModelAndView result;
		domain.Thread hilo;
		String hiloTitle;
		Collection<Comment> comments;
		Integer lastPage;
			
		hilo=threadService.findOne(id);
		comments = threadService.findCommentsByPage(id, p);
		hiloTitle = hilo.getTitle();
		lastPage = threadService.calculateLastPage(null, hilo);
		
		result =new ModelAndView("thread/display");
		result.addObject("hilo",hilo);
		result.addObject("comments",comments);
		
		Comment comment=new Comment();
		
		comment.setCreationMoment(new Date());
		comment.setThread(hilo);
		comment.setUser(userService.findOneByPrincipal());
		result.addObject("comment", comment);
		result.addObject("p", p);
		result.addObject("lastPage",lastPage);
		//devuelve hilo mas sus comentarios
		return result;
			
		
		
	}
	
	// Creation --------------------------------------------------------------------------
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public ModelAndView saveComment(@Valid Comment comment, BindingResult binding){
		
		ModelAndView result;
		Integer page;
		
		page = threadService.calculateLastPage(comment, null);

		result= new ModelAndView("redirect:display.do?id="+comment.getThread().getId()+"&p="+page+"");
		
		if(binding.hasErrors()){
			
			System.out.println(binding.toString());
			
		}else{
			try{
			commentService.save(comment);
			//debemos comprobar si se guarda o no para guardar tambien el hilo
			}catch(Throwable op){
				op.printStackTrace();
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		domain.Thread thread;
		
        thread  = threadService.create();
        result = new ModelAndView("thread/edit");
        
        result.addObject("thread", thread);
        
        result.addObject("actionURI", "thread/edit.do");
        
        return result;
}
	
	// Edition ------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int threadId){
		domain.Thread thread=threadService.findOne(threadId);
		
		ModelAndView result=createEditModelAndView(thread);
		
		return result;
	}
	
		
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid domain.Thread thread, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(thread);
		} else {
			try {
				threadService.save(thread);
				result = new ModelAndView("redirect:list.do");
			 } catch (Throwable oops) {
				result=createEditModelAndView(thread, "commit.error");
				System.out.println(oops.getStackTrace());
			}
		}
		
		return result;
	}
	

	
	@RequestMapping("/delete")
	public ModelAndView deleteThread(@RequestParam int id){
		
		
		domain.Thread thread=threadService.findOne(id);
		
		
		//TODO
		
		return new ModelAndView("delete");
		
	}
	
	//login from census, this make a http get to census module and get the json output, after tries to make a login with json output
	//if the person is no present in the bd, save the new person and log in the context.
	//we are to trust the username census give us is unique
	//if the person is present in the bd, log in the context
	//TODO hacerlo bien, nueva funcionalidad
	@RequestMapping("/loginFromCensus")
	public ModelAndView loginFromCensus(String username, HttpServletRequest httpRequest) throws JsonParseException, JsonMappingException, IOException{
		
		//implementar
		ModelAndView result;
		
		System.out.println(username);
		
		// Encontrar en Censos con JSon
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		//Document doc=Jsoup.connect("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username).get();
		//System.out.println(doc.toString());
		
		// Si da error, el usuario no está en el censo
		
		CensusUser censusUser=null;
		String nameFinal = "";
		try{
		
			try{
			censusUser=objectMapper.readValue(new URL("http://localhost:8080/ADMCensus/census/findCensusByVote.do?idVotacion="+1),CensusUser.class);
			}catch( JsonParseException e){
				System.out.println(e.toString());	
				return loginFromCensusFrom();
			}
		System.out.println(censusUser.toString());
		Assert.isTrue(censusUser.getUsername()!=null);
		
		for (String name: censusUser.getVoto_por_usuario().keySet()){
			
			if(name.equals(username)){
				
				nameFinal=name;
			}
			
		}
		
		
		}catch(Exception e){
			
			
			return loginFromCensusFrom();
		}
		
		// Si no, adelante
		
		if(nameFinal.equals("")){
			
			return loginFromCensusFrom();
		}else if(userService.findByUsername(nameFinal)!=null){//esta en la base de datos
			
			// Login
			
			loginMakeFromCensus(userService.findByUsername(username).getUserAccount(), httpRequest);
			
			result=new ModelAndView("list");
			
			
		}else{ // Al no estar, se le registra
			User user;
			UserAccount userAccount;
			
			user = userService.create(username);
			userAccount = user.getUserAccount();
			
			userService.save(user);
			loginMakeFromCensus(userAccount, httpRequest);
			
			result=new ModelAndView("list");
		}
		return result;
	}
	
	@RequestMapping(value="loginFromCensusForm", method = RequestMethod.GET)
	public ModelAndView loginFromCensusFrom(){
		ModelAndView result;
		
		result = new ModelAndView("user/loginFromCensusForm");
		
		return result;
	}
	
	
	public void loginMakeFromCensus(UserAccount user, HttpServletRequest request){
		
		  try {
	            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
	        	System.out.println(request.toString());
	        	System.out.println("contraseña pepe de base de datos: "+user.getPassword());
	            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), null);
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
	
	// Ancillary methods ----------------------------------------------------------------------
	
	private ModelAndView createEditModelAndView(domain.Thread thread){
		ModelAndView result;
		
	
		result = createEditModelAndView(thread, null);
		
		
		return result;
	}
	
	public ModelAndView createEditModelAndView(domain.Thread thread, String message) {
		ModelAndView result;
		
		result = new ModelAndView("thread/edit");
		
		result.addObject("thread", thread);
		result.addObject("message", message);
		
		return result;
	}
	
	
	
	
	
	

//CREACIÓN LOGIN FROM CABINA DE VOTACIÓN, NOS VIENE UNA ID Y UN TOKEN PARA COMPRAR CON AUTENTIFICACIÓN IMPLEMENTAR ES NECESARIO IMPLEMENTAR - 
	
//CONEXION CON AUTENTICICAION A TRAVES DE JSON PARA PODER LOGUEAR DESDE EL CABINA DE VOTACIÓN
	
	
	
	
	
	//CREACIÓN DE HILOS DESDE CREACIÓN/ADMINISTRACIÓN DE VOTACIONES, LES DEBEMOS DE DAR UN LINK PARA QUE NOS TRAIGA Y CREEMOS UNOS NOSOTROS
	
	
	@RequestMapping("/createThreadFromVotacion")
	public ModelAndView createTreadFromVotacion(String name){
		
		User user=userService.findByUsername("customer");
		
		domain.Thread nuevo=new domain.Thread();
		nuevo.setCreationMoment(new Date());
		nuevo.setDecription("Hilo sobre la votación: "+name);
		nuevo.setUser(user);
		nuevo.setTitle("Votación "+name);
		nuevo.setComments(new ArrayList<Comment>());
		
		threadService.save(nuevo);
		return 	new ModelAndView("redirect:list.do");
		
		//CreacionAdminVotaciones/#/create
	}
	
}
