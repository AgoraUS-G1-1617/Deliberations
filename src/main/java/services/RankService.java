package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Rank;
import domain.User;
import repositories.RankRepository;

@Service
@Transactional
public class RankService {

	// Managed repository -----------------------------------------------------
	
	@Autowired
	private RankRepository rankRepository;

	// Supporting services ----------------------------------------------------
	
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private CommentService commentService;

	// Constructors -----------------------------------------------------------
	
	public RankService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Rank create() {
		Rank result;
		
		result = new Rank();
				
		return result;
	}

	public Rank findOne(int rankId) {
		return rankRepository.findOne(rankId);
	}

	public Collection<Rank> findAll() {
		return rankRepository.findAll();
	}

	public Rank save(Rank rank) {
		return rankRepository.save(rank);
		
	}

	public void delete(Rank rank) {
		rankRepository.delete(rank);
	}

	// Other business methods -------------------------------------------------

	public Rank calculateRank(User user){
		Rank result = findRankForNumber(0); // Nuevo
		
		int nThreads;
		int nComments;
		int nRatings;
		
		
		Collection<Rank> requirementsRanks = new ArrayList<Rank>();	
		
		//Ratings del user
		nThreads = threadService.countThreadCreatedByUserGiven(user);
		//Threads del user
		nComments = commentService.countCommentsCreatedByUserGiven(user);
		//Comments del user
		nRatings = ratingService.countRatingCreatedByUserGiven(user);
		requirementsRanks = sortWithRequirementsRanks();
		
		if (!((nThreads == 0) && (nComments == 0) && (nRatings == 0))) { // Al menos alguna iteracción
			result = findRankForNumber(1);
			for (Rank r : requirementsRanks) {
	
				Integer finalThreads = nThreads - r.getMinThreads();
				Integer finalComments = nComments - r.getMinComments();
				Integer finalRatings = nRatings - r.getMinRatings();

				if((finalThreads >= 0) && (finalComments >= 0) && (finalRatings >= 0)) { //Todos deben ser mayor que 0 para que cumplan condición
					result = r;
					break;
				}

			}
		}
		
		
		return result;
	}
	
	public Rank findRankForNumber(Integer number){
		Rank result;
		result = rankRepository.findRankForNumber(number);
		
		return result;
		
	}
	
	public Collection<Rank> sortWithRequirementsRanks(){
		Collection<Rank> result;
		
		result = new ArrayList<Rank>();
		result = rankRepository.sortWithRequirementsRanks();
		
		return result;
		
	}
	
	public Collection<Rank> sortAllRanks(){
		Collection<Rank> result;
		
		result = new ArrayList<Rank>();
		result = rankRepository.sortAllRanks();
		
		return result;
		
	}
	
	
	
}