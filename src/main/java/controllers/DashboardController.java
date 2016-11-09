
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Thread;
import services.ThreadService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private ThreadService threadService;

	// Constructors -----------------------------------------------------------

	public DashboardController() {
		super();
	}

	

	// Listing-------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<domain.Thread> threadMostComments;
		Collection<domain.Thread> threadLeastComments;
		Collection<domain.Thread> threadMoreRating;
	
		
		threadMostComments=threadService.findThreadWithMoreComments();
		threadLeastComments=threadService.findThreadWithLessComments();
		threadMoreRating=threadService.findThreadMoreRating();
		
		String uri = "dashboard/list";
		String requestURI = "dashboard/list.do";
		
		
		
		result = createListModelAndView(requestURI, uri);
		result.addObject("threadMostComments", threadMostComments);
		result.addObject("threadLeastComments", threadLeastComments);
		result.addObject("threadMoreRating", threadMoreRating);
		
		return result;
	}
	
	

	// Other bussiness method ---------------------------------------------------------------
	protected ModelAndView createListModelAndView(
			String requestURI, String uri) {
		ModelAndView result;

		result = new ModelAndView(uri);
		result.addObject("requestURI", requestURI);

		return result;
	}
}
