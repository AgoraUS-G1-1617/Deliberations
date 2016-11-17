package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	@Query("select h.comments from Thread h where h.id=?1")
	Collection<Comment> findCommentsOfHilo(int idHilo);
	
	@Query("select u.comments from User u where u.userAccount.id=?1")
	Collection<Comment> findCommentsOfUser(int idUserAccount);
	
	@Query("select c from Comment c where c.creationMoment>?1")
	Collection<Comment> findCommentsInTheLastHours(Date creation);
	
	@Query("select c from Comment c where c.erase is true")
	Collection<Comment> findAllCommentsDeleted();
	
	@Query("select c from Comment c where c.erase is false or c.erase is null")
	Collection<Comment> findAllCommentsNotDeleted();
	
	@Query("select count(c)*100/(select h.comments.size from Thread h where h.id=?1) from Comment c where c.user.userAccount.id=?2 and c.thread.id=?1)")
	Double findRatioOfCommentsOfUserInHilo(int idHilo,int idUserAccount);
}
