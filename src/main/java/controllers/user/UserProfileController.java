package controllers.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Rank;
import domain.User;
import services.CommentService;
import services.KarmaService;
import services.MessageService;
import services.RankService;
import services.RatingService;
import services.ThreadService;
import services.UserService;

@Controller
@RequestMapping("/user")
public class UserProfileController extends AbstractController {

	// Supporting services -----------------------

	@Autowired
	private UserService userService;

	@Autowired
	private RankService rankService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private KarmaService karmaService;
	
	// Constructors -----------------------------------------------------------

	public UserProfileController() {
		super();
	}

	// ------------------------------------------------------------------------

	@RequestMapping("/profile")
	public ModelAndView profile(HttpServletRequest  request, @RequestParam(required=false) Integer userId) {
		ModelAndView result;
		User user;
		Rank rank;
		Rank rankTemp;
		Integer numRanks;
		Cookie[] cookies;
		String cookieValue;
		int messagesSent, messagesReceived, threadsCreated, commentsCreated,ratingsCreated;
		Collection<Rank> nextRanks;
		Integer cont;
		List<Integer> karmaOfUser;

		

		/*Datos necesario para la vista de su rango*/
		if(userId != null){
			user = userService.findOne(userId);
			Assert.notNull(user);
			result = new ModelAndView("user/publicProfile");
		}else{
			user = userService.findOneByPrincipal();
			Assert.notNull(user);
			result = new ModelAndView("user/profile");
		}
		
		
		rank = rankService.calculateRank(user);
		numRanks = rankService.sortAllRanks().size();
		
		/*Datos necesario para las estadisticas del usuario*/
		messagesSent = messageService.countMessagesSentByUser();
		messagesReceived = messageService.countMessagesReceivedtByUser();
		threadsCreated   = threadService.countThreadCreatedByUserGiven(user);
		commentsCreated  = commentService.countCommentsCreatedByUserGiven(user);
		ratingsCreated	 = ratingService.countRatingCreatedByUserGiven(user);
		nextRanks = new ArrayList<Rank>();
		cont = rank.getNumber();
		karmaOfUser = karmaService.karmaOfUser(user.getId());

		while (cont < numRanks - 1) {
			rankTemp = rankService.findRankForNumber(cont + 1);
			nextRanks.add(rankTemp);
			cont = cont + 1;

		}
		
		
		/*Por defecto la aplicación está en ingles y obtenemos cookies*/
		cookies = request.getCookies();
		
		for(Cookie i: cookies){
			if(i.getName().equals("language")){
				cookieValue = i.getValue();
				result.addObject("cookieValue", cookieValue);
				break;
			}
			
		}

		
		result.addObject("user", user);
		result.addObject("rank", rank);
		result.addObject("nextRanks", nextRanks);
		result.addObject("numRanks", numRanks);
		result.addObject("messagesSent", messagesSent);
		result.addObject("messagesReceived", messagesReceived);
		result.addObject("threadsCreated", threadsCreated);
		result.addObject("commentsCreated", commentsCreated);
		result.addObject("ratingsCreated", ratingsCreated);
		result.addObject("karmaOfUser", karmaOfUser);

		return result;
	}
	
}