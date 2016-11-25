package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Thread;
import domain.User;
import repositories.CommentRepository;
import security.LoginService;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comment create() {
		Comment result;

		result = new Comment();

		return result;
	}

	public Comment findOne(int commentId) {
		return commentRepository.findOne(commentId);
	}

	public Collection<Comment> findAll() {
		return commentRepository.findAll();
	}

	public void save(Comment comment) {
		// Associated business rules:
		//	- The thread that is associated to the comment passed as parameter must not be closed
		Assert.isTrue(!comment.getThread().isClosed());
		
		commentRepository.save(comment);
	}

	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}

	// Other business methods -------------------------------------------------

	public Collection<Comment> findCommentsOfHilo(int idHilo) {
		Collection<Comment> result;

		result = new ArrayList<Comment>();
		result = commentRepository.findCommentsOfHilo(idHilo);

		return result;
	}

	public Collection<Comment> findCommentsOfUser() {
		Collection<Comment> result;
		
		result = new ArrayList<Comment>();
		result = commentRepository.findCommentsOfUser(LoginService.getPrincipal().getId());

		return result;
	}
	
	public Collection<Comment> commentsOfUser(int userId) {
		Collection<Comment> result;
		
		result = commentRepository.commentsOfUser(userId);

		return result;
	}

	public Collection<Comment> findCommentsInTheLastHours(Date creation) {
		Collection<Comment> result;
		
		result = new ArrayList<Comment>();
		result = commentRepository.findCommentsInTheLastHours(creation);

		return result;
	}

	public Collection<Comment> findAllCommentsDeleted() {
		Collection<Comment> result;

		result = new ArrayList<Comment>();
		result = commentRepository.findAllCommentsDeleted();

		return result;
	}

	public Collection<Comment> findAllCommentsNotDeleted() {
		Collection<Comment> result;

		result = new ArrayList<Comment>();
		result = commentRepository.findAllCommentsNotDeleted();

		return result;
	}

	public Double findRatioOfCommentsOfUserInHilo(int idHilo) {
		Double result;

		result = 0.0;
		result = commentRepository.findRatioOfCommentsOfUserInHilo(idHilo, LoginService.getPrincipal().getId());
		
		return result;
	}
	
	
	public int countCommentsCreatedByUser(){
		int res;
		User user;
		
		user = userService.findOneByPrincipal();
		
		Assert.notNull(user);
		
		res = commentRepository.countCommentsCreatedByUserId(user.getId());
		
		return res;
	}
	
	/**
	 * Maps the id of a given thread in a collection of threads to the last comment of such a thread
	 * @param threads A collection of threads
	 * @return A map, indicating the last comment for each thread id in the map keys
	 */
	public Map<Integer,Comment> findLastComments(Collection<Thread> threads) {
		Map<Integer,Comment> result;
		Collection<Comment> comments;
		
		result = new HashMap<Integer,Comment>();
		
		for (Thread thread: threads) {
			// WARNING! Two or more comments may be retrieved, since the creation moment precision is set at minutes, it is
			// likely that a collection with more than one element will be retrieved. Although it may seem inaccurate to retrieve
			// the first element of the iterator, it does not matter since the moment displayed will be exactly the same.
			comments = commentRepository.findLastComment((int)thread.getId());
			if(!comments.isEmpty()) {
				result.put((int)thread.getId(),comments.iterator().next());
			}
		}

		return result;
	}
	
	public int countCommentsCreatedByUserGiven(User user){
		int res;
		
		Assert.notNull(user);
		
		res = commentRepository.countCommentsCreatedByUserIdGiven(user.getId());
		
		return res;
	}
}