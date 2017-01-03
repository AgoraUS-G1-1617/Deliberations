package services;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.User;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class UserServiceTest extends AbstractTest {
	
	// Service to test --------------------------------------------------------

	@Autowired
	private UserService userService;
	
	// Supporting services ----------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	/**
	 * @Test Create, list and delete a user
	 * @result The user persist into the database successfully
	 */
	@Test
	public void possitiveUserCRUDTest() {

		User usuario;
		Collection<User> todos;
		Collection<User> usuariosZeroComentarios;
		User buscado;
		User principal;


		usuario = userService.create("userTest");
		usuario.setAutonomous_community("Andalucia");
		usuario.setEmail("userTest@gmail.com");
		usuario.setGenre("Masculino");
		usuario.setSurname("surname");
		
		usuario = userService.setUserProperties(usuario, usuario.getUserAccount());
		
		usuario = userService.save(usuario);
		
        authenticate("UserTest");
    
		buscado = userService.findByUsername("userTest");
		todos = userService.findAll();
		usuariosZeroComentarios = userService.findUserWithZeroComments();
		principal = userService.findOneByPrincipal();
		
        unauthenticate();

		Assert.notNull(buscado);
		Assert.isTrue(todos.contains(usuario));
		Assert.isTrue(usuariosZeroComentarios.contains(usuario));
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(usuario));
		
		userService.delete(usuario);
		
		todos =userService.findAll();
		buscado = userService.findOne(usuario.getId());
		
		Assert.isNull(buscado);
		Assert.isTrue(!todos.contains(usuario));

	}
	
	
	/**
	 * @Test Create a user with invalid email
	 * @result We try to save a user so
	 *         <code>ConstraintViolationException</code> is expected
	 */
	@Test(expected=ConstraintViolationException.class)
	public void negativeCreateTest() {
		
		User usuario = userService.create("userTest");
		usuario.setEmail("errorEmail");

		usuario = userService.save(usuario);
		
		Assert.isTrue(userService.findAll().contains(usuario));
	}
	
	
}