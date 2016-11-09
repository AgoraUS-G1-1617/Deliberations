package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rating;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	
	@Query("select t.ratings from Thread t where t.id=?1")
	Collection<Rating> findRatingsOfThread(int idThread);
	
	@Query("select t.ratings from User t where t.userAccount.id=?1")
	Collection<Rating> findRatingsOfUser(int idUserAccount);
	
	@Query("select sum(r.rate) from Rating r where r.id=?1")
	Integer totalRating(int idRate);
}
