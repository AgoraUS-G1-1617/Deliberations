package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import domain.Rating;
import domain.User;
import repositories.RatingRepository;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RatingTest extends AbstractTest {

	
	// Service to test --------------------------------------------------------

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThreadService threadService;

	
	// Test cases ------------------------------------------------------------
	

	/**
	 * @Test New rating a thread and remove this
	 * @result We persist successfully rating to the database and we check.
	 */
	@Test
	public void possitiveTestRatingCRUDTest() {
		authenticate("user1");
		Rating newRating;
		domain.Thread thread;
		Collection<Rating> ratings;
		
		thread = threadService.findOne(7);
		
		newRating = ratingService.create();
		newRating.setRate(3);
		newRating.setThread(thread);

		newRating = ratingService.save(newRating);
		ratings = ratingService.findAll();
		newRating = ratingService.findOne(newRating.getId());
		
		assertEquals(true, ratings.size()==4);
		assertEquals(true, ratingRepository.exists(newRating.getId()));
		
		ratingService.delete(newRating);
		assertEquals(false, ratingRepository.exists(newRating.getId()));
		unauthenticate();
	}
	

	/**
	 * @Test Find ratings for user and thread
	 * @result We persist successfully rating to the database and we check.
	 */
	@Test
	public void possitiveTestRatingOtherMethods() {
		authenticate("user3");
		Rating newRating;
		domain.Thread thread;
		int threadId = 7;
		Collection<Rating> ratingsOfThread;
		Collection<Rating> ratingsOfUser;
		Rating ratingThrearUser;
		int countRatings;
		User user;

		thread = threadService.findOne(threadId);
		newRating = ratingService.create();
		newRating.setRate(3);
		newRating.setThread(thread);

		newRating = ratingService.save(newRating);
		ratingsOfThread = ratingService.findRatingsOfThread(threadId);
		assertEquals(true, ratingsOfThread.size()==3);
		
		ratingsOfUser = ratingService.findRatingsOfUser();
		user = userService.findOneByPrincipal();
		countRatings = ratingService.countRatingCreatedByUserGiven(user);
		assertEquals(true, ratingsOfUser.size()==countRatings);
		
		
		ratingThrearUser = ratingService.findRatingOfUserAtThread(threadId);
		assertEquals(true, ratingThrearUser.equals(newRating));


		unauthenticate();
	}
	
	/**
	 * @Test Recover rate of rating
	 * @result We persist successfully rating to the database and we check.
	 */
	@Test
	public void possitiveTestRatingOtherMethods2() {
		authenticate("user3");
		Rating newRating;
		domain.Thread thread;
		int threadId = 7;
		int ratingId = 16;
		Integer value;
		Integer value2;

		thread = threadService.findOne(threadId);
		newRating = ratingService.create();
		newRating.setRate(3);
		newRating.setThread(thread);

		newRating = ratingService.save(newRating);
		value = ratingService.totalRating(ratingId);
		value2 = ratingService.findOne(ratingId).getRate();
		
		Assert.isTrue(value.equals(value2));

		unauthenticate();
	}
	
	/**
	 * @Test New rating with violation of constraint
	 * @result We try to persists rating but get <code>ConstraintViolationException</code>
	 */
	@Test(expected = ConstraintViolationException.class)
	public void negativeTestRating() {
		authenticate("user1");
		Rating newRating;
		domain.Thread thread;
		
		thread = threadService.findOne(7);
		
		newRating = ratingService.create();
		newRating.setRate(10);
		newRating.setThread(thread);

		newRating = ratingService.save(newRating);
		
		assertEquals(true, ratingRepository.exists(newRating.getId()));
	
	}

	/**
	 * @Test Edit rating the other user 
	 * @result We try to persists the new rating but give <code>IllegalArgumentException</code>
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeTestRating2() {
		authenticate("user3");
		Rating newRating;
		int ratingId = 16;

		newRating = ratingService.findOne(ratingId);
		newRating.setRate(1);

		newRating = ratingService.save(newRating);
		assertEquals(true, ratingRepository.exists(newRating.getId()));
	
	}
}
