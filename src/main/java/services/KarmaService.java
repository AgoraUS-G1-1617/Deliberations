package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
	
	@Autowired
	private ThreadService threadService;

	// Constructors -----------------------------------------------------------
	
	public KarmaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Karma create() {
		Karma result;
		User user;
		
		result = new Karma();
		user = userService.findOneByPrincipal();
		
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
		karma = karmaRepository.karmaOfComment(commentId)!=null?(karmaRepository.karmaOfComment(commentId)):0;
		positives = karmaRepository.karmaOfCommentPositive(commentId)!=null?(karmaRepository.karmaOfCommentPositive(commentId)):0;
		negatives = karmaRepository.karmaOfCommentNegative(commentId)!=null?(karmaRepository.karmaOfCommentNegative(commentId)):0;
		
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
		karma = karmaRepository.karmaOfUser(userId)!=null?(karmaRepository.karmaOfUser(userId)):0;
		positives = karmaRepository.karmaOfUserPositive(userId)!=null?(karmaRepository.karmaOfUserPositive(userId)):0;
		negatives = karmaRepository.karmaOfUserNegative(userId)!=null?(karmaRepository.karmaOfUserNegative(userId)):0;
		
		
		result.add(karma);
		result.add(positives);
		result.add(negatives);
		
		return result;
	}
	
	/**
	 * This method returns the Karma of the logged user at a comment
	 * @param commentId
	 * @return the Karma entity if exist
	 */
	public Karma karmaOfUserAtComment(int commentId){
		Karma result;
		User user;
		
		user = userService.findOneByPrincipal();
		
		if(!new ArrayList<Karma>(karmaRepository.karmaOfUserAtComment(commentId, user.getId())).isEmpty()){
			
			result = new ArrayList<Karma>(karmaRepository.karmaOfUserAtComment(commentId, user.getId())).get(0);
			
		}else{
			
			result = null;
			
		}		
		
		return result;
	}
	
	/**
	 * This method returns a Map, each key (the comment id) link with the karma of that key 
	 * @param threadId
	 * @return a map with the result
	 */
	public HashMap<Integer, List<Integer>> karmaOfThread(int threadId, int page){
		HashMap<Integer, List<Integer>> result;
		List<Comment> comments;
		
		result = new HashMap<Integer, List<Integer>>();
		comments = new ArrayList<Comment>(threadService.findCommentsByPage(threadId, page));
		
		for(Comment c: comments){
			result.put(c.getId(), karmaOfComment(c.getId()));
		}
		
		return result;
	}
}