package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Rating;
import domain.Thread;
import domain.User;
import repositories.ThreadRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ThreadService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ThreadRepository threadRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public ThreadService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Thread create() {
		// Associated business rules:
		//	- It must be an authenticated user who performs this use case
		Thread result;
		User user;
		Collection<Comment> comments;
		Collection<Rating> ratings;
		Date date;

		user = userService.findOneByPrincipal();
		Assert.notNull(user);
		
		result = new Thread();
		comments = new ArrayList<Comment>();
		ratings = new ArrayList<Rating>();
		date = new Date(System.currentTimeMillis() - 1000);

		result.setUser(user);
		result.setComments(comments);
		result.setRatings(ratings);
		result.setCreationMoment(date);
		result.setLastUpdate(date);
		result.setClosed(false);

		return result;
	}

	public Thread findOne(int threadId) {
		// Associated business rules:
		//	- The id passed as parameter must be greater than 0
		//	- The id passed as parameter must be associated to an existing thread
		Assert.isTrue(threadId > 0);
		
		Thread result;
		
		result = threadRepository.findOne(threadId);
		Assert.notNull(result);
		
		return result;
	}

	public Page<Thread> findAll(Pageable page) {
		// Associated business rules:
		//	- The Pageable object passed as parameter must not be null
		Assert.notNull(page);
		
		Page<Thread> result;
		
		result = threadRepository.findAllSortedByDate(page);
		
		return result;
	}

	public Thread save(Thread thread) {
		// Associated business rules:
		//	- It must be a logged in user who performs this use case
		//	- The thread passed as parameter must be associated to the user that is logged in
		User principal;
		Thread dbThread;
		
		principal = userService.findOneByPrincipal();
		Assert.notNull(principal);

		if (thread.getId() == 0){
			dbThread = this.create();
		} else {
			dbThread = this.findOne(thread.getId());
		}
		
		dbThread.setTitle(thread.getTitle());
		dbThread.setDecription(thread.getDecription());
		
		thread = dbThread;
		
		Assert.isTrue(principal.equals(thread.getUser()), "threadService.save not propietary");
//		Assert.isTrue(!thread.getClosed(), "threadService.save is closed");

		return threadRepository.save(thread);
	}

	// Other business methods -------------------------------------------------

	public void refreshLastUpdate(Thread thread){
		// Associated business rules:
		//	- The thread passed as parameter must not be null
		Assert.notNull(thread);
		
		Date date;

		thread = this.findOne(thread.getId());
		date = new Date(System.currentTimeMillis() - 1000);
		thread.setLastUpdate(date);

		threadRepository.save(thread);
	}

	public Collection<Thread> findThreadWithMoreComments() {
		Collection<Thread> result;

		result = threadRepository.findThreadWithMoreComments();

		return result;
	}

	public Collection<Thread> findThreadWithLessComments() {
		Collection<Thread> result;

		result = threadRepository.findThreadWithLessComments();

		return result;
	}

	public Collection<Thread> findThreadOfUser() {
		// Associated business rules:
		//	- It must be a logged in user who performs this use case
		Collection<Thread> result;
		UserAccount principal;
		
		principal = LoginService.getPrincipal();
		Assert.notNull(principal);

		result = threadRepository.findThreadOfUser(principal.getId());

		return result;
	}

	public Collection<Thread> findThreadWithTitle(String title) {
		// Associated business rules:
		//	- The title passed as parameter must not be null
		Assert.notNull(title);
		
		Collection<Thread> result;

		result = threadRepository.findThreadWithTitle(title);

		return result;
	}

	public Collection<Thread> findThreadMoreRating() {
		Collection<Thread> result;

		result = threadRepository.findThreadMoreRating();

		return result;
	}

	public Collection<Thread> findThreadLessRating() {
		Collection<Thread> result;

		result = threadRepository.findThreadLessRating();

		return result;
	}

	public List<Comment> findCommentsByPage(int valueOf, int p) {
		domain.Thread hilo;
		Integer numberRows;
		List<Comment> paginatedComments;

		hilo = findOne(valueOf);
		numberRows = p * 10;
		paginatedComments = new ArrayList<Comment>();

		for (int i = numberRows - 10; i <= hilo.getComments().size() - 1; i++) {
			if (i < numberRows) {
				paginatedComments.add((Comment) hilo.getComments().toArray()[i]);
			} else {
				break;
			}
		}

		return paginatedComments;
	}

	public Integer calculateLastPage(Comment comment, domain.Thread hilo) {
		Double numberComments;
		Double pageDouble;
		Integer result;

		if (comment != null)
			numberComments = (double) comment.getThread().getComments().size() + 1;
		else
			numberComments = (double) hilo.getComments().size();

		// Calcula qué pagina corresponde al mensaje recién creado, en decimal

		pageDouble = numberComments / 10;

		// Se le aplica un redondeo siempre hacia arriba para el cálculo final
		// de la página

		result = (int) Math.ceil(pageDouble);

		return result;
	}
	
	public Thread open(domain.Thread thread) {
		// Associated business rules:
		//	- The thread passed as parameter must have been created by the current principal
		//	- The given thread must be closed
		Assert.isTrue(thread.getUser().equals(userService.findOneByPrincipal()));
		Assert.isTrue(thread.getClosed());
		
		Thread result;
		
		thread.setClosed(false);
		
		result = threadRepository.save(thread);
		
		return result;
	}
	
	public Thread close(domain.Thread thread) {
		// Associated business rules:
		//	- The thread passed as parameter must have been created by the current principal
		//	- The given thread must not be already closed
		Assert.isTrue(thread.getUser().equals(userService.findOneByPrincipal()));
		Assert.isTrue(!thread.getClosed());
		
		Thread result;
		
		thread.setClosed(true);
		
		result = threadRepository.save(thread);
		
		return result;
	}
	
	public int countThreadsCreatedByPrincipal(){
		// Associated business rules:
		//	- It must be a logged in user who performs this use case
		int result;
		User principal;
		
		principal = userService.findOneByPrincipal();
		Assert.notNull(principal);
		
		result = countThreadsCreatedByGivenUser(principal);
		
		return result;
	}
	
	public int countThreadsCreatedByGivenUser(User user){
		// Associated business rules:
		//	- The user passed as parameter must not be null
		Assert.notNull(user);
		
		int result;
		
		result = threadRepository.countThreadCreatedByUserId(user.getId());
		
		return result;
	}
}