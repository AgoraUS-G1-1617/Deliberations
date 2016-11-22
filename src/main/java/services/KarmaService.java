package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	
	/**
	 * This method returns the Karma info about a Comment
	 * @param commentId
	 * @return an array with the results, the first position is the sum of Karma, the second the positive points and
	 * 		   the third the negative points
	 */
	public List<Integer> karmaOfComment(int commentId){
		List<Integer> result;
		int karma;
		int positives;
		int negatives;
		
		result = new ArrayList<Integer>();
		karma = karmaRepository.karmaOfComment(commentId);
		positives = karmaRepository.karmaOfCommentPositive(commentId);
		negatives = karmaRepository.karmaOfCommentNegative(commentId);
		
		result.add(karma);
		result.add(positives);
		result.add(negatives);
		
		return result;
	}
	
	/**
	 * This method returns the Karma info about a User
	 * @param userId
	 * @return an array with the results, the first position is the sum of Karma, the second the positive points and
	 * 		   the third the negative points
	 */
	public List<Integer> karmaOfUser(int userId){
		List<Integer> result;
		int karma;
		int positives;
		int negatives;
		
		result = new ArrayList<Integer>();
		karma = karmaRepository.karmaOfUser(userId);
		positives = karmaRepository.karmaOfUserPositive(userId);
		negatives = karmaRepository.karmaOfUserNegative(userId);
		
		result.add(karma);
		result.add(positives);
		result.add(negatives);
		
		return result;
	}

	public Karma karmaOfUserAtComment(int commentId, int userId){
		Karma result;
		
		result = new ArrayList<Karma>(karmaRepository.karmaOfUserAtComment(commentId, userId)).get(0);
		
		return result;
	}
}