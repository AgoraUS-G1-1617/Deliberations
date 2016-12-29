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

import domain.Rank;
import domain.User;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class RankServiceTest extends AbstractTest {
	
	// Service to test --------------------------------------------------------

	@Autowired
	private RankService rankService;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThreadService threadService;

	// Test cases -------------------------------------------------------------
	
	/**
	 * @Test Creation of rank and check that it doesn't belong a user
	 * @result The rank persist into the database successfully
	 */
	@Test
	public void possitiveRankCRUDTest() {
		User usuario;
		Rank rango;
		Collection<Rank> todosRangos;
		Rank buscado;
		Rank calculado;
		domain.Thread hiloCreado;
		
		usuario = userService.create("userTest");
		
		rango = rankService.create();
		rango.setTitle("TituloTest");
		rango.setNumber(5);
		rango.setDescriptionEn("DescripcionIngles");
		rango.setDescriptionEs("DescripcionEspañol");
		rango.setIcon("https://www.images.com/icon1");
		rango.setMinComments(40);
		rango.setMinRatings(40);
		rango.setMinThreads(40);
		
		
		usuario = userService.save(usuario);
        authenticate("UserTest");
        
		rango = rankService.save(rango);
		
		buscado = rankService.findOne(rango.getId());
		todosRangos = rankService.findAll();
		
		
		Assert.notNull(buscado);
		Assert.isTrue(todosRangos.contains(rango));
		
		calculado = rankService.calculateRank(usuario);
		Assert.isTrue(calculado.getNumber() == 0);
		
		hiloCreado = threadService.create();
		hiloCreado.setTitle("HiloTest");
		hiloCreado.setDecription("DescripcionTest");
		
		threadService.save(hiloCreado);
		
		calculado = rankService.calculateRank(usuario);
		Assert.isTrue(calculado.getNumber() == 1);
		unauthenticate();
		
		rankService.delete(rango);
		todosRangos = rankService.sortAllRanks();
		Assert.isTrue(!todosRangos.contains(rango));
		
	}
	
	
	/**
	 * @Test Creation of rank with blank title
	 * @result We try to save a rank so
	 *         <code>ConstraintViolationException</code> is expected
	 */
	@Test(expected=ConstraintViolationException.class)
	public void negativeCreateTest01() {
		
		Rank rango;
		
		rango = rankService.create();
		rango.setTitle("");
		rango.setNumber(2);
		rango.setDescriptionEn("DescripcionIngles");
		rango.setDescriptionEs("DescripcionEspañol");
		rango.setIcon("https://www.images.com/icon1");
		rango.setMinComments(40);
		rango.setMinRatings(40);
		rango.setMinThreads(40);
		
        
		rango = rankService.save(rango);
		
		Assert.isTrue(rankService.findAll().contains(rango));
	}
	
	
	/**
	 * @Test Creation of rank with incorrect email and incorrect icon
	 * @result We try to save a rank so
	 *         <code>ConstraintViolationException</code> is expected
	 */
	@Test(expected=ConstraintViolationException.class)
	public void negativeCreateTest02() {

		Rank rango;

		rango = rankService.create();
		rango.setTitle("");
		rango.setNumber(200);
		rango.setDescriptionEn("DescripcionIngles");
		rango.setDescriptionEs("DescripcionEspañol");
		rango.setIcon("errorEnEmail");
		rango.setMinComments(40);
		rango.setMinRatings(40);
		rango.setMinThreads(40);
		
        
		rango = rankService.save(rango);
		
		Assert.isTrue(rankService.findAll().contains(rango));
	}
	
}