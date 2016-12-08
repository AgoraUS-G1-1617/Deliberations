package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Rating;
import domain.Thread;
import services.RatingService;
import services.ThreadService;

@Controller
@RequestMapping("/rating")
public class RatingController extends AbstractController {

	// Supporting services ----------------------------------------------------

	@Autowired
	private RatingService ratingService;

	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private ThreadController threadController;

	// Constructors -----------------------------------------------------------

	public RatingController() {
		super();
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int threadId, @RequestParam int value) {
		ModelAndView result;
		Thread thread = threadService.findOne(threadId);
		Rating rating = ratingService.findRatingOfUserAtThread(threadId);
		
		result = threadController.seeThread(threadId, 1);
		
		if(rating == null) {
			rating = ratingService.create();
			rating.setThread(thread);
		}
		
		rating.setRate(value);
		ratingService.save(rating);
		
		return result;
	}
}
