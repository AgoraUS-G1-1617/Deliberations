package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Rating;
import domain.Token;
import domain.User;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ServiceTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private ThreadService threadService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RatingService ratingService;

	// Tests --------------------------------------------------------------

	// Create an user
	// --------------------------------------------------------------

	@Test
	public void testCreateUser() {

		User result;

		result = userService.create("Usuario prueba");

		result.getUserAccount().setPassword("prueba");
		result.setEmail("prueba@gmail.com");
		result.setSurname("prueba");

		userService.save(result);

		System.out.println("El usuario ha sido creado correctamente.");

	}

	// Create a thread
	// --------------------------------------------------------------

	@Test
	public void testCreateThread() {

		authenticate("user1");
		domain.Thread result;

		result = threadService.create();

		result.setTitle("Titulo prueba");
		result.setDecription("Texto prueba");

		threadService.save(result);

		System.out.println("El hilo ha sido creado correctamente.");
		unauthenticate();

	}

	// Create a comment
	// --------------------------------------------------------------

	@Test
	public void testCreateComment() {

		authenticate("user1");
		Comment result;
		domain.Thread hilo;

		result = new Comment();

		hilo = threadService.findOne(6);
		result.setCreationMoment(new Date());

		result.setThread(hilo);
		result.setUser(userService.findOneByPrincipal());
		result.setText("Texto comentario");

		commentService.save(result);

		System.out.println("El comentario ha sido creado correctamente.");
		unauthenticate();

	}

	// List all threads
	// --------------------------------------------------------------

	@Test
	public void testListAllThreads() {

		authenticate("user1");

		Collection<domain.Thread> result;

		result = threadService.findAll();

		System.out.println("La lista de hilos es: " + result);
		unauthenticate();

	}

	@Test
	public void testAuthConnection()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		ObjectMapper objectMapper;
		UserAccount userAccount;
		String tokenToVerify;
		Token resultOfToken;

		objectMapper = new ObjectMapper();
		userAccount = new UserAccount();

		// Se asignan usuario y contraseña a una cuenta de usuario nueva porque
		// si se recuperase una ya creada incluiría el hash
		// de la contraseña en lugar de la contraseña en sí
		userAccount.setUsername("pabcargar2");
		userAccount.setPassword("pabcargar2");

		// Se crea el token a verificar
		tokenToVerify = loginService.verifyToken(userAccount);

		// Se realiza la petición de consulta
		resultOfToken = objectMapper.readValue(new URL("http://localhost/Auth/api/checkToken?token=" + tokenToVerify),
				Token.class);

		System.out.println("resultado del token: " + resultOfToken.isValid());

		Assert.isTrue(resultOfToken.isValid());
	}

	// Create rating
	// -----------------------------------------------------------------

	@Test
	public void createRating() {
		authenticate("user1");
		Integer totalAntes = ratingService.findAll().size();
		Rating res = ratingService.create();
		domain.Thread thread = threadService.findOne(7);
		res.setRate(4);
		res.setThread(thread);
		ratingService.save(res);
		Integer totalDespues = ratingService.findAll().size();
		Assert.isTrue(totalAntes < totalDespues);
		unauthenticate();

	}

}
