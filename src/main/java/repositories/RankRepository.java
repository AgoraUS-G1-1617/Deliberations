package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rank;


@Repository
public interface RankRepository extends JpaRepository<Rank, Integer> {
	
	
	@Query("select r from Rank r where r.number=?1")
	Rank findRankForNumber(Integer number);
	
	@Query("select r from Rank r where r.minThreads != 0 and r.minComments != 0 and r.minRatings != 0 order by number")
	Collection<Rank> sortWithRequirementsRanks();
	
	@Query("select r from Rank r order by number")
	Collection<Rank> sortAllRanks();
}
