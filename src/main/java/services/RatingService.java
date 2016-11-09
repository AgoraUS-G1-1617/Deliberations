package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Rating;
import domain.User;
import repositories.RatingRepository;
import security.LoginService;

@Service
@Transactional
public class RatingService {

	// Managed repository
	@Autowired
	private RatingRepository ratingRepository;

	// Supporting services
	@Autowired
	private UserService userService;

	// Constructors
	public RatingService() {
		super();
	}

	// Simple CRUD methods
	public Rating create() {
		Rating res = new Rating();
		User user=userService.findOneByPrincipal();
		res.setUser(user);
		return res;
	}

	public Rating findOne(int ratingId) {
		return ratingRepository.findOne(ratingId);
	}

	public Collection<Rating> findAll() {
		return ratingRepository.findAll();
	}

	public void save(Rating rating) {
		ratingRepository.save(rating);
	}

	public void delete(Rating rating) {
		ratingRepository.delete(rating);
	}

	// Other business methods

	public Collection<Rating> findRatingsOfThread(int idThread){
		Collection<Rating> res= new ArrayList<Rating>();
		res=ratingRepository.findRatingsOfThread(idThread);
		return res;
	}
	
	public Collection<Rating> findRatingsOfUser(){
		Collection<Rating> res= new ArrayList<Rating>();
		res=ratingRepository.findRatingsOfUser(LoginService.getPrincipal().getId());
		return res;
	}

	public Integer totalRating(int idRate){
		Integer res=0;
		res=ratingRepository.totalRating(idRate);
		return res;
	}
}