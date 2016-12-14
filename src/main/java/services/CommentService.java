package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
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

	@Autowired
	private ThreadService threadService;

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
		Assert.isTrue(!comment.getThread().getClosed());
		
		// No se puede editar un comentario
		Assert.isTrue(comment.getId() == 0);

		commentRepository.save(comment);
		threadService.refreshLastUpdate(comment.getThread());
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
	
	public int countCommentsCreatedByUserGiven(User user){
		int res;
		
		Assert.notNull(user);
		
		res = commentRepository.countCommentsCreatedByUserIdGiven(user.getId());
		
		return res;
	}
}