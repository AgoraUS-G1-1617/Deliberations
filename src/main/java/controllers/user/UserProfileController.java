/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Rank;
import domain.User;
import services.RankService;
import services.UserService;

@Controller
@RequestMapping("/user")
public class UserProfileController extends AbstractController {

	// Supporting services -----------------------

	@Autowired
	private UserService userService;

	@Autowired
	private RankService rankService;

	// Constructors -----------------------------------------------------------

	public UserProfileController() {
		super();
	}

	// ------------------------------------------------------------------------

	@RequestMapping("/profile")
	public ModelAndView login() {
		ModelAndView result;
		User user;

		Rank rank;
		Rank rankTemp;
		Integer numRanks;

		user = userService.findOneByPrincipal();
		rank = rankService.calculateRank(user);
		numRanks = rankService.sortAllRanks().size();

		Collection<Rank> nextRanks = new ArrayList<Rank>();
		Integer cont = rank.getNumber();

		while (cont < numRanks - 1) {
			rankTemp = rankService.findRankForNumber(cont + 1);
			nextRanks.add(rankTemp);
			cont = cont + 1;

		}

		result = new ModelAndView("user/profile");
		result.addObject("user", user);
		result.addObject("rank", rank);
		result.addObject("nextRanks", nextRanks);
		result.addObject("numRanks", numRanks);

		return result;
	}

}