
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/about-us")
public class AboutController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AboutController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/teams")
	public ModelAndView cookies() {
		ModelAndView result;

		result = new ModelAndView("about-us/developers");

		return result;
	}

}