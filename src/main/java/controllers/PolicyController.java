
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/policy")
public class PolicyController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public PolicyController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/cookies")
	public ModelAndView cookies() {
		ModelAndView result;

		result = new ModelAndView("policies/cookies");

		return result;
	}

	@RequestMapping(value = "/terms")
	public ModelAndView terms() {
		ModelAndView result;

		result = new ModelAndView("policies/terms");

		return result;
	}

}