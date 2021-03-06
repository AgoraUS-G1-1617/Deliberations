package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Thread;


@Repository
public interface ThreadRepository extends JpaRepository<Thread, Integer> {
	
	@Query("select t from Thread t where t.comments.size=(select max(h.comments.size) from Thread h)")
	Collection<Thread> findThreadWithMoreComments();
	
	@Query("select t from Thread t where t.comments.size=(select min(h.comments.size) from Thread h)")
	Collection<Thread> findThreadWithLessComments();
	
	@Query("select t from Thread t where t.user.userAccount.id=?1")
	Collection<Thread> findThreadOfUser(int idUserAccount);
	
	@Query("select t from Thread t where t.title=?1")
	Collection<Thread> findThreadWithTitle(String title);
	
	@Query("select r.thread from Rating r group by r.thread having sum(r.rate)>= all(select sum(w.rate) from Rating w group by w.thread)")
	Collection<Thread> findThreadMoreRating();

	@Query("select r.thread from Rating r group by r.thread having sum(r.rate)<= all(select sum(w.rate) from Rating w group by w.thread)")
	Collection<Thread> findThreadLessRating();

	@Query("select count(t) from Thread t where t.user.id = ?1")
	int countThreadCreatedByUserId(int id);
	
	@Query("select t from Thread t order by t.lastUpdate desc")
	Page<Thread> findAllSortedByDate(Pageable page);
}
