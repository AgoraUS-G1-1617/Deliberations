package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Karma;
import domain.User;
import repositories.KarmaRepository;

@Service
@Transactional
public class KarmaService {

	// Managed repository -----------------------------------------------------
	
	@Autowired
	private KarmaRepository karmaRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------
	
	public KarmaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Karma create() {
		Karma result;
		User user;
		
		result = new Karma();
		user=userService.findOneByPrincipal();
		
		result.setUser(user);
		
		return result;
	}

	public Karma findOne(int karmaId) {
		return karmaRepository.findOne(karmaId);
	}

	public Collection<Karma> findAll() {
		return karmaRepository.findAll();
	}

	public Karma save(Karma karma) {
		Karma result;
		
		result = karmaRepository.save(karma);
		
		return result;
	}

	public void delete(Karma karma) {
		karmaRepository.delete(karma);
	}

	// Other business methods -------------------------------------------------
	
	/**
	 * This method set the value to a Karma Entity
	 * @param karma
	 * @param comment
	 * @param value
	 * @return the Karma entity with the value correct
	 */
	public Karma setKarma(Karma karma, Comment comment, String value){
		Assert.isTrue(value.equals("up") || value.equals("down"));
		Karma result;
		
		result = karma;
		
		result.setValue(value.equals("up") ? 1 : -1);
		result.setComment(comment);
		
		return result;
	}
	
	public int karmaOfComment(int commentId){
		int result;
		
		result = karmaRepository.karmaOfComment(commentId);
		
		return result;
	}
	
	public int karmaOfUser(int userId){
		int result;
		
		result = karmaRepository.karmaOfUser(userId);
		
		return result;
	}

	public Karma karmaOfUserAtComment(int commentId, int userId){
		Karma result;
		
		result = new ArrayList<Karma>(karmaRepository.karmaOfUserAtComment(commentId, userId)).get(0);
		
		return result;
	}
}