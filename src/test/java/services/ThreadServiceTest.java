package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Thread;
import domain.User;
import utilities.AbstractTest;
import utilities.UtilTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ThreadServiceTest extends AbstractTest {
	
	// Service to test --------------------------------------------------------

	@Autowired
	private ThreadService threadService;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	// Test cases -------------------------------------------------------------
	
	@Test
	public void testCreate01() {
		Thread created;
		User principal;
		
		principal = userService.findByUsername("user1");
		
		authenticate("user1");
		
		created = threadService.create();
		
		Assert.notNull(created, "El m�todo ha devuelto null. Se esperaba un hilo vac�o");
		Assert.notNull(created.getComments(), "No se ha asignado una lista de comentarios");
		Assert.notNull(created.getRatings(), "No se ha asignado una lista de valoraciones");
		Assert.isTrue(created.getComments().isEmpty(), "La lista de comentarios no est� vac�a");
		Assert.isTrue(created.getRatings().isEmpty(), "La lista de valoraciones no est� vac�a");
		Assert.notNull(created.getUser(), "No se ha asignado ning�n usuario al hilo");
		Assert.isTrue(created.getUser().equals(principal), "El usuario que se ha asignado no es el que est� logueado en el sistema");
		Assert.isTrue(!created.getClosed(), "El hilo est� cerrado por defecto");
		Assert.notNull(created.getCreationMoment(), "No se ha asignado la fecha de creaci�n");
		
		unauthenticate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreate02() {
		threadService.create();
	}
	
	@Test
	public void testFindOne01() {
		Thread result;
		
		result = threadService.findOne(7);
		
		Assert.notNull(result, "El m�todo ha devuelto null. Se esperaba el hilo de id n�mero 7");
		Assert.isTrue(result.getTitle().equals("He votado al PP y..."), "Se esperaba un hilo distinto al que se ha devuelto");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindOne02() {
		threadService.findOne(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindOne03() {
		threadService.findOne(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindOne04() {
		threadService.findOne(6);
	}
	
	@Test
	public void testFindAll01() {
		Pageable pageable;
		Page<Thread> result;
		
		pageable = new PageRequest(0,2);
		
		result = threadService.findAll(pageable);
		
		Assert.notNull(result, "El m�todo ha devuelto null. Se esperaba un objeto del tipo Page");
		Assert.isTrue(result.getTotalPages() == 2, "Se esperaba que el m�todo devolviera 2 p�ginas y ha devuelto " + result.getTotalPages());
		Assert.isTrue(result.getNumberOfElements() == 2, "Se esperaba que el m�todo devolviera 3 hilos en la p�gina actual y ha devuelto " + result.getNumberOfElements());
		Assert.isTrue(result.getTotalElements() == 3, "Se esperaba que el m�todo devolviera 3 hilos y ha devuelto " + result.getNumberOfElements());
	}
	
	@Test
	public void testFindAll02() {
		Pageable pageable;
		Page<Thread> result;
		
		pageable = new PageRequest(0,3);
		
		result = threadService.findAll(pageable);
		
		Assert.notNull(result, "El m�todo ha devuelto null. Se esperaba un objeto del tipo Page");
		Assert.isTrue(result.getTotalPages() == 1, "Se esperaba que el m�todo devolviera 1 p�gina y ha devuelto " + result.getTotalPages());
		Assert.isTrue(result.getNumberOfElements() == 3, "Se esperaba que el m�todo devolviera 3 hilos en la p�gina actual y ha devuelto " + result.getNumberOfElements());
		Assert.isTrue(result.getTotalElements() == 3, "Se esperaba que el m�todo devolviera 3 hilos y ha devuelto " + result.getNumberOfElements());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindAll03() {
		threadService.findAll(null);
	}
	
	@Test
	public void testSave01() {
		Thread thread;
		
		authenticate("user1");
		
		thread = threadService.create();
		thread.setTitle("JUnit test thread");
		thread.setDecription("JUnit test description");
		
		thread = threadService.save(thread);
		
		unauthenticate();
		
		Assert.notNull(thread, "El m�todo ha devuelto null. Se esperaba un objeto del tipo Thread recientemente persistido en base de datos");
		Assert.isTrue(thread.getId() > 0, "El id del hilo en cuesti�n no es mayor que uno, lo que implica que no se ha persistido en base de datos");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSave02() {
		Thread thread;
		
		authenticate("user1");
		
		thread = threadService.create();
		thread.setTitle("JUnit test thread");
		thread.setDecription("JUnit test description");
		
		unauthenticate();
		
		threadService.save(thread);
	}
	
	@Test
	public void testSave03() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread1");
		thread = threadService.findOne(threadId);
		
		authenticate("user1");
		
		thread.setTitle("JUnit test thread");
		thread.setDecription("JUnit test description");
		
		threadService.save(thread);
		
		unauthenticate();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSave04() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread1");
		thread = threadService.findOne(threadId);
		
		authenticate("user1");
		
		thread.setTitle("JUnit test thread");
		thread.setDecription("JUnit test description");
		
		unauthenticate();
		authenticate("user2");
		
		threadService.save(thread);
		
		unauthenticate();
	}
	
	@Test
	public void testRefreshLastUpdate01() {
		Date lastUpdateBefore;
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread1");
		thread = threadService.findOne(threadId);
		lastUpdateBefore = thread.getLastUpdate();
		
		threadService.refreshLastUpdate(thread);
		
		Assert.isTrue(thread.getLastUpdate().after(lastUpdateBefore), "La �ltima actualizaci�n del hilo no se ha calculado correctamente");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRefreshLastUpdate02() {
		threadService.refreshLastUpdate(null);
	}
	
	@Test
	public void testFindThreadWithMoreComments01() {
		Collection<Thread> result;
		
		result = threadService.findThreadWithMoreComments();
		
		Assert.isTrue(result.size() == 2, "Se esperaba una colecci�n con dos hilos m�s comentados y el tama�o es de " + result.size());
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread1"))), "El thread1 no est� contenido en la colecci�n y se esperaba que lo estuviera");
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread2"))), "El thread2 no est� contenido en la colecci�n y se esperaba que lo estuviera");
	}
	
	@Test
	public void testFindThreadWithLessComments01() {
		Collection<Thread> result;
		
		result = threadService.findThreadWithLessComments();
		
		Assert.isTrue(result.size() == 1, "Se esperaba una colecci�n con un hilo menos comentados y el tama�o es de " + result.size());
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread3"))), "El thread3 no est� contenido en la colecci�n y se esperaba que lo estuviera");
	}
	
	@Test
	public void testFindThreadOfUser01() {
		Collection<Thread> result;
		
		authenticate("user3");
		
		result = threadService.findThreadOfUser();
		
		Assert.isTrue(result.size() == 1, "Se esperaba una colecci�n con un hilo y el tama�o es de " + result.size());
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread3"))), "El thread3 no est� contenido en la colecci�n y se esperaba que lo estuviera");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindThreadOfUser02() {
		threadService.findThreadOfUser();
	}
	
	@Test
	public void testFindThreadWithTitle01() {
		Collection<Thread> result;
		String title;
		
		title = "He votado al PP y...";
		result = threadService.findThreadWithTitle(title);
		
		Assert.isTrue(result.size() == 1, "Se esperaba una colecci�n con un hilo y el tama�o es de " + result.size());
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread1"))), "El thread1 no est� contenido en la colecci�n y se esperaba que lo estuviera");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFindThreadWithTitle02() {
		threadService.findThreadWithTitle(null);
	}
	
	@Test
	public void testFindThreadWithMoreRatings01() {
		Collection<Thread> result;
		
		result = threadService.findThreadMoreRating();
		
		Assert.isTrue(result.size() == 1, "Se esperaba una colecci�n con un hilo y el tama�o es de " + result.size());
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread1"))), "El thread1 no est� contenido en la colecci�n y se esperaba que lo estuviera");
	}
	
	@Test
	public void testFindThreadWithLessRatings01() {
		Collection<Thread> result;
		
		result = threadService.findThreadLessRating();
		
		Assert.isTrue(result.size() == 1, "Se esperaba una colecci�n con un hilo y el tama�o es de " + result.size());
		Assert.isTrue(result.contains(threadService.findOne(UtilTest.getIdFromBeanName("thread2"))), "El thread2 no est� contenido en la colecci�n y se esperaba que lo estuviera");
	}
	
	@Test
	public void testOpen01() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread3");
		thread = threadService.findOne(threadId);
		
		authenticate("user3");
		
		thread = threadService.open(thread);
		
		unauthenticate();
		
		Assert.isTrue(!thread.getClosed(), "El hilo no se ha abierto como se esperaba");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOpen02() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread3");
		thread = threadService.findOne(threadId);
		
		authenticate("user2");
		
		threadService.open(thread);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testOpen03() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread2");
		thread = threadService.findOne(threadId);
		
		authenticate("user2");
		
		threadService.open(thread);
	}
	
	@Test
	public void testClose01() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread2");
		thread = threadService.findOne(threadId);
		
		authenticate("user2");
		
		thread = threadService.close(thread);
		
		unauthenticate();
		
		Assert.isTrue(thread.getClosed(), "El hilo no se ha cerrado como se esperaba");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClose02() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread2");
		thread = threadService.findOne(threadId);
		
		authenticate("user3");
		
		threadService.close(thread);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testClose03() {
		Thread thread;
		int threadId;
		
		threadId = UtilTest.getIdFromBeanName("thread3");
		thread = threadService.findOne(threadId);
		
		authenticate("user3");
		
		threadService.close(thread);
	}
	
	@Test
	public void testCountThreadsCreatedByPrincipal01() {
		int result;
		
		authenticate("user1");
		
		result = threadService.countThreadsCreatedByPrincipal();
		
		unauthenticate();
		
		Assert.isTrue(result == 1, "Se esperaba que el resultado fuera 1 y ha sido " + result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCountThreadsCreatedByPrincipal02() {
		threadService.countThreadsCreatedByPrincipal();
	}
	
	@Test
	public void testCountThreadsCreatedByGivenUser01() {
		int result;
		User user;
		
		user = userService.findByUsername("user1");
		
		result = threadService.countThreadsCreatedByGivenUser(user);
		
		Assert.isTrue(result == 1, "Se esperaba que el resultado fuera 1 y ha sido " + result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCountThreadsCreatedByGivenUser02() {
		threadService.countThreadsCreatedByGivenUser(null);
	}
}