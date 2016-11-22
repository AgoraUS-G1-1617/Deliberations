package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Karma;


@Repository
public interface KarmaRepository extends JpaRepository<Karma, Integer> {
	
	@Query("select sum(k.value) from Karma k where k.comment.id=?1")
	Integer karmaOfComment(int commentId);
	
	@Query("select sum(k.value) from Karma k where k.user.id=?1")
	Integer karmaOfUser(int userId);
	
	@Query("select k from Karma k where k.comment.id=?1 and k.user.id=?2")
	Collection<Karma> karmaOfUserAtComment(int commentId, int userId);
}
