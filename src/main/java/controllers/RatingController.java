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

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Rating;
import services.RatingService;
import services.ThreadService;


@Controller
@RequestMapping("/rating")
public class RatingController extends AbstractController {
	
	// Supporting services -----------------------
	
	@Autowired
	private RatingService ratingService;
		
	@Autowired
	private ThreadService threadService;
	
	// Constructors -----------------------------------------------------------

	public RatingController() {
		super();
	}
	
	
	// Edition ------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int threadId){
		domain.Thread thread=threadService.findOne(threadId);
		domain.Rating rating=ratingService.create();
		rating.setThread(thread);
		List<Integer> ratings=new ArrayList<Integer>();
		ratings.add(1);
		ratings.add(2);
		ratings.add(3);
		ratings.add(4);
		ratings.add(5);
		ModelAndView result=createEditModelAndView(rating);
		result.addObject("ratings", ratings);
		
		return result;
	}
	
		
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid domain.Rating rating, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(rating);
		} else {
			try {
				ratingService.save(rating);
				result = new ModelAndView("redirect:../thread/list.do");
			 } catch (Throwable oops) {
				result=createEditModelAndView(rating, "commit.error");
				System.out.println(oops.getStackTrace());
			}
		}
		
		return result;
	}
	

	

	
	// Ancillary methods ----------------------------------------------------------------------
	
	private ModelAndView createEditModelAndView(domain.Rating rating){
		ModelAndView result;
		
	
		result = createEditModelAndView(rating, null);
		
		
		return result;
	}
	
	

	
	public ModelAndView createEditModelAndView(domain.Rating rating, String message) {
		ModelAndView result;
		
		result = new ModelAndView("rating/edit");
		
		result.addObject("rating", rating);
		result.addObject("message", message);
		
		return result;
	}
	

}
