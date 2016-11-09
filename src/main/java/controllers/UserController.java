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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Token;
import domain.User;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Supporting services -----------------------

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserDetailsService userDetailsService;

	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	// ------------------------------------------------------------------------

	// TODO a servicio
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView result;
		Authority authority;
		UserAccount userAccount;

		userAccount = new UserAccount();
		authority = new Authority();

		authority.setAuthority("CUSTOMER");
		userAccount.addAuthority(authority);

		result = new ModelAndView("user/login");
		result.addObject("userAccount", userAccount);

		return result;
	}

	@RequestMapping("/loginMake")
	public ModelAndView loginMake(@Valid UserAccount userAccount, BindingResult bindingResult,
			HttpServletRequest request) throws IOException {

		ModelAndView result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String tokenToVerify;
		Token response;

		// Se comprueban errores en el formulario de login
		if (bindingResult.hasErrors()) {
			result = LoginModelAndView(null);

			// En caso de no haber errores
		} else {

			// Se monta el token a verificar para el usuario
			tokenToVerify = loginService.verifyToken(userAccount);

			// Se recupera la respuesta a la petición

			response = objectMapper.readValue(new URL("http://localhost/Auth/api/checkToken?token=" + tokenToVerify),
					Token.class);

			// Se comprueba que la respuesta recibida sea válida
			if (response.isValid()) {

				Md5PasswordEncoder md5 = new Md5PasswordEncoder();

				try {
					// Se comprueba que el usuario que accede exista ya en
					// Deliberaciones y se inicia sesión
					Assert.isTrue(loginService.loadUserByUsername(userAccount.getUsername()).getPassword()
							.equals(md5.encodePassword(userAccount.getPassword(), null)));

					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
							userAccount.getUsername(), md5.encodePassword(userAccount.getPassword(), null));

					token.setDetails(new WebAuthenticationDetails(request));

					DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();

					authenticator.setUserDetailsService(userDetailsService);

					Authentication authentication = authenticator.authenticate(token);

					SecurityContextHolder.getContext().setAuthentication(authentication);

				} catch (Exception e) {
					// En caso de no existir en Deliberaciones se le da de alta

					User usuario = userService.create(userAccount.getUsername());

					userService.save(usuario);

				}

				result = new ModelAndView("redirect:/");

				// En caso de que la respuesta recibida no sea válida, se
				// deniega el acceso
			} else {

				result = LoginModelAndView("login.error");

			}
		}

		return result;

	}

	// Ancillary methods
	// ----------------------------------------------------------------------

	private ModelAndView LoginModelAndView(String message) {
		ModelAndView result;

		result = new ModelAndView("user/login");
		result.addObject("message", message);

		return result;
	}

}
