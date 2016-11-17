package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		date = new Date();

		result.setUser(user);
		result.setComments(comments);
		result.setRatings(ratings);
		result.setCreationMoment(date);

		return result;
	}

	public Thread findOne(int threadId) {
		return threadRepository.findOne(threadId);
	}

	public Collection<Thread> findAll() {
		return threadRepository.findAll();
	}

	public void save(Thread thread) {
		Date date;

		date = new Date(System.currentTimeMillis() - 1000);
		thread.setCreationMoment(date);

		threadRepository.save(thread);
	}

	public void delete(Thread thread) {
		threadRepository.delete(thread);
	}

	// Other business methods -------------------------------------------------

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
				System.out.println((Comment) hilo.getComments().toArray()[i]);
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
}