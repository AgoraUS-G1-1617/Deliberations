package services;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.User;
import repositories.CommentRepository;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CommentTest extends AbstractTest {

	
	// Service to test --------------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentService commentService;
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ThreadService threadService;

	
	// Test cases ------------------------------------------------------------
	


	/**
	 * @Test Creation of comments
	 * @result The comment persist into the database successfully
	 */
	@Test
	public void possitiveCommentCRUDTest() {
		authenticate("user1");
		Comment comment;
		User user;
		domain.Thread thread;
		
		user = userService.findOneByPrincipal();
		thread = threadService.findOne(7);
		comment = commentService.create();
		
		comment.setCreationMoment(Calendar.getInstance().getTime());
		comment.setText("Test");
		comment.setUser(user);
		comment.setThread(thread);
		
		comment = commentService.save(comment);
		Assert.isTrue(commentRepository.exists(comment.getId()));
		Assert.isTrue(commentService.findOne(comment.getId()).getText().equals("Test"));
		Assert.isTrue(commentService.findOne(comment.getId()).getUser().equals(user));
		Assert.isTrue(commentService.findOne(comment.getId()).getThread().equals(thread));
		unauthenticate();
	}
	
	/**
	 * @Test Other business methods 
	 * @result The methods returns the expected values
	 */
	@Test
	public void possitiveCommentOtherMethodsTest() {
		authenticate("user1");
		Integer commentsOfHilo;
		Integer commentsOfUser;
		Integer commentsInTheLastHours;
		Double findRatioOfCommentsOfUserInHilo;
		int countCommentsCreatedByUser;
		
		commentsOfHilo = commentService.findCommentsOfHilo(7).size();
		Assert.isTrue(commentsOfHilo.equals(3));

		commentsOfUser = commentService.findCommentsOfUser().size(); 
		Assert.isTrue(commentsOfUser.equals(2));
		
		commentsInTheLastHours =  commentService.findCommentsInTheLastHours(Calendar.getInstance().getTime()).size();
		Assert.isTrue(commentsInTheLastHours.equals(0));
		
		findRatioOfCommentsOfUserInHilo = commentService.findRatioOfCommentsOfUserInHilo(7);
		Assert.isTrue(findRatioOfCommentsOfUserInHilo.equals(33.0));
		
		countCommentsCreatedByUser = commentService.countCommentsCreatedByUser();
		Assert.isTrue(countCommentsCreatedByUser==2);
		
		unauthenticate();
	}
	
	
	
	
	/**
	 * @Test Edition of comments
	 * @result We try to edit an comment so <code>IllegalArgumentException</code> is expected
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negativeCRUDTes() {
		authenticate("user1");
		Comment comment;
		
		comment = commentService.findOne(10);
		comment.setText("Test");
		
		commentService.save(comment);
		
		unauthenticate();
	}
	
	/**
	 * @Test Comments created by User
	 * @result We try to get the comments of null user so <code>IllegalArgumentException</code> is expected
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void negativeCommentOtherMethodsTest() {
		int countCommentsCreatedByUser;
		
		countCommentsCreatedByUser = commentService.countCommentsCreatedByUser();
	}
	
	/**
	 * @Test Comments created by Given User
	 * @result We try to get the comments of null user so <code>IllegalArgumentException</code> is expected
	 */
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void negativeCommentOtherMethodsTest1() {
		int countCommentsCreatedByUser;
		
		countCommentsCreatedByUser = commentService.countCommentsCreatedByUserGiven(null);
	}
	
}
