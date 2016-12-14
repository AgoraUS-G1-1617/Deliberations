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
		Thread result;
		User user;
		Collection<Comment> comments;
		Collection<Rating> ratings;
		Date date;

		result = new Thread();
		user = userService.findOneByPrincipal();
		comments = new ArrayList<Comment>();
		ratings = new ArrayList<Rating>();
		date = new Date(System.currentTimeMillis() - 1000);

		result.setUser(user);
		result.setComments(comments);
		result.setRatings(ratings);
		result.setCreationMoment(date);
		result.setLastUpdate(date);
		result.setClosed(false);
		result.setErase(false);

		return result;
	}

	public Thread findOne(int threadId) {
		return threadRepository.findOne(threadId);
	}

	public Page<Thread> findAll(Pageable page) {
		return threadRepository.findAllSortedByDate(page);
	}

	public void save(Thread thread) {
		User actUser;
		Thread dbThread;
		
		actUser = userService.findOneByPrincipal();

		if (thread.getId() == 0){
			dbThread = this.create();
			
		} else {			
			dbThread = this.findOne(thread.getId());
		}
		
		dbThread.setTitle(thread.getTitle());
		dbThread.setDecription(thread.getDecription());
		
		thread = dbThread;
		
		Assert.isTrue(actUser.equals(thread.getUser()), "threadService.save not propietary");
		Assert.isTrue(!thread.getClosed(), "threadService.save is closed");
		Assert.isTrue(!thread.getErase(), "threadService.save is erased");


		threadRepository.save(thread);
	}

	// Other business methods -------------------------------------------------

	public void refreshLastUpdate(Thread t){
		Date date;

		t = this.findOne(t.getId());
		date = new Date(System.currentTimeMillis() - 1000);
		t.setLastUpdate(date);

		threadRepository.save(t);
	}

	public Collection<Thread> findThreadWithMoreComments() {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadWithMoreComments();

		return result;
	}

	public Collection<Thread> findThreadWithLessComments() {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadWithLessComments();

		return result;
	}

	public Collection<Thread> findThreadOfUser() {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadOfUser(LoginService.getPrincipal().getId());

		return result;
	}

	public Collection<Thread> findThreadWithTitle(String title) {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadWithTitle(title);

		return result;
	}

	public Collection<Thread> findThreadAvailables() {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadAvailables();

		return result;
	}

	public Collection<Thread> findThreadMoreRating() {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadMoreRating();

		return result;
	}

	public Collection<Thread> findThreadLessRating() {
		Collection<Thread> result;

		result = new ArrayList<Thread>();
		result = threadRepository.findThreadLessRating();

		return result;
	}

	public List<Comment> findCommentsByPage(Integer valueOf, Integer p) {
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
	
	public int countThreadCreatedByUser(){
		int res;
		User user;
		
		user = userService.findOneByPrincipal();
		
		Assert.notNull(user);
		
		res = threadRepository.countThreadCreatedByUserId(user.getId());
		
		return res;
	}
	
	public int countThreadCreatedByUserGiven(User user){
		int res;
		
		Assert.notNull(user);
		
		res = threadRepository.countThreadCreatedByUserIdGiven(user.getId());
		
		return res;
	}
}