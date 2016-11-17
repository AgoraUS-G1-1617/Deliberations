package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Comment;
import repositories.CommentRepository;
import security.LoginService;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;

	// Supporting services ----------------------------------------------------

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
}