package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Rating;
import domain.User;
import repositories.RatingRepository;
import security.LoginService;

@Service
@Transactional
public class RatingService {

	// Managed repository -----------------------------------------------------
	
	@Autowired
	private RatingRepository ratingRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------
	
	public RatingService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Rating create() {
		Rating result;
		User user;
		
		result = new Rating();
		user=userService.findOneByPrincipal();
		
		result.setUser(user);
		
		return result;
	}

	public Rating findOne(int ratingId) {
		return ratingRepository.findOne(ratingId);
	}

	public Collection<Rating> findAll() {
		return ratingRepository.findAll();
	}

	public void save(Rating rating) {
		// Associated business rules:
		//	- It must be a user who performs this use case
		//	- The user who saves the rating must be the user who is logged into the system
		User principal;
		
		principal = userService.findOneByPrincipal();
		
		Assert.notNull(principal);
		Assert.isTrue(rating.getUser().equals(principal));
		
		ratingRepository.save(rating);
	}

	public void delete(Rating rating) {
		ratingRepository.delete(rating);
	}

	// Other business methods -------------------------------------------------

	public Collection<Rating> findRatingsOfThread(int idThread){
		Collection<Rating> result;
		
		result = new ArrayList<Rating>();
		result = ratingRepository.findRatingsOfThread(idThread);
		
		return result;
	}
	
	public Collection<Rating> findRatingsOfUser(){
		Collection<Rating> result;
		
		result = new ArrayList<Rating>();
		result=ratingRepository.findRatingsOfUser(LoginService.getPrincipal().getId());
		
		return result;
	}
	
	public Rating findRatingOfUserAtThread(int threadId){
		Rating result;
		
		result = ratingRepository.findRatingsOfUserAtThread(LoginService.getPrincipal().getId(), threadId);
		
		return result;
	}

	public Integer totalRating(int idRate){
		Integer result;
		
		result = 0;
		result = ratingRepository.totalRating(idRate);
		
		return result;
	}
	
	public int countRatingCreatedByUserGiven(User user){
		int res;
		
		Assert.notNull(user);
		
		res = ratingRepository.countRatingCreatedByUserIdGiven(user.getId());
		
		return res;
	}
}