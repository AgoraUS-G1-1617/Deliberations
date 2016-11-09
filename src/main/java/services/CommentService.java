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

	// Managed repository
	@Autowired
	private CommentRepository commentRepository;

	// Supporting services

	// Constructors
	public CommentService() {
		super();
	}

	// Simple CRUD methods
	public Comment create() {
		Comment res = new Comment();
		return res;
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

	// Other business methods

	public Collection<Comment> findCommentsOfHilo(int idHilo){
		Collection<Comment> res= new ArrayList<Comment>();
		res=commentRepository.findCommentsOfHilo(idHilo);
		return res;
	}
	
	public Collection<Comment> findCommentsOfUser(){
		Collection<Comment> res= new ArrayList<Comment>();
		res=commentRepository.findCommentsOfUser(LoginService.getPrincipal().getId());
		return res;
	}
	
	public Collection<Comment> findCommentsInTheLastHours(Date creation){
		Collection<Comment> res= new ArrayList<Comment>();
		res=commentRepository.findCommentsInTheLastHours(creation);
		return res;
	}
	
	public Collection<Comment> findAllCommentsDeleted(){
		Collection<Comment> res= new ArrayList<Comment>();
		res=commentRepository.findAllCommentsDeleted();
		return res;
	}
	
	public Collection<Comment> findAllCommentsNotDeleted(){
		Collection<Comment> res= new ArrayList<Comment>();
		res=commentRepository.findAllCommentsNotDeleted();
		return res;
	}
	public Double findRatioOfCommentsOfUserInHilo(int idHilo){
		Double res=0.0;
		res=commentRepository.findRatioOfCommentsOfUserInHilo(idHilo, LoginService.getPrincipal().getId());
		return res;
	}
}