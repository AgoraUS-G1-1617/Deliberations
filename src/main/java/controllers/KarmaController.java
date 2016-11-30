package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Karma;
import services.CommentService;
import services.KarmaService;

@Controller
@RequestMapping("/karma")
public class KarmaController extends AbstractController {

	// Supporting services -----------------------

	@Autowired
	private KarmaService karmaService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ThreadController threadController;

	// Constructors -----------------------------------------------------------

	public KarmaController() {
		super();
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/setKarma", method = RequestMethod.GET)
	public ModelAndView setKarma(@RequestParam int commentId, @RequestParam String value ) {
		ModelAndView result;
		Comment comment;
		Karma karma;
		
		if(karmaService.karmaOfUserAtComment(commentId)==null){
			
			karma = karmaService.create();
			
		}else{
			
			karma = karmaService.karmaOfUserAtComment(commentId);
			
		}
		
		comment = commentService.findOne(commentId);
		karma = karmaService.setKarma(karma, comment, value);
		
		karmaService.save(karma);
		
		result = threadController.seeThread(comment.getThread().getId(), 1);

		return result;
	}

	// Ancillary methods ------------------------------------------------------


}
