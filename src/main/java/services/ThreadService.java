package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Comment;
import domain.Rating;
import domain.Thread;
import domain.User;
import repositories.ThreadRepository;
import security.LoginService;

@Service
@Transactional
public class ThreadService {

	// Managed repository
	@Autowired
	private ThreadRepository threadRepository;

	// Supporting services
	@Autowired
	private UserService userService;

	// Constructors
	public ThreadService() {
		super();
	}

	// Simple CRUD methods
	public Thread create() {
		Thread res = new Thread();
		User user=userService.findOneByPrincipal();	
		Collection<Comment> comments=new ArrayList<Comment>();
		Collection<Rating> ratings=new ArrayList<Rating>();
		Date date=new Date();
		res.setUser(user);
		res.setComments(comments);
		res.setRatings(ratings);
		res.setCreationMoment(date);
		return res;
	}

	public Thread findOne(int threadId) {
		return threadRepository.findOne(threadId);
	}

	public Collection<Thread> findAll() {
		return threadRepository.findAll();
	}

	public void save(Thread thread) {
		Date date=new Date(System.currentTimeMillis()-1000);
		thread.setCreationMoment(date);
		threadRepository.save(thread);
	}

	public void delete(Thread thread) {
		threadRepository.delete(thread);
	}

	// Other business methods
	
	
	public Collection<Thread> findThreadWithMoreComments(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadWithMoreComments();
		return res;
	}
	
	public Collection<Thread> findThreadWithLessComments(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadWithLessComments();
		return res;
	}
	
	public Collection<Thread> findThreadOfUser(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadOfUser(LoginService.getPrincipal().getId());
		return res;
	}
	
	public Collection<Thread> findThreadWithTitle(String title){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadWithTitle(title);
		return res;
	}
	
	public Collection<Thread> findThreadAvailables(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadAvailables();
		return res;
	}
	
	public Collection<Thread> findThreadMoreRating(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadMoreRating();
		return res;
	}
	
	public Collection<Thread> findThreadLessRating(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadLessRating();
		return res;
	}
	
	public List<Comment> findCommentsByPage(Integer valueOf, Integer p){
		domain.Thread hilo;
		Integer numberRows;
		List<Comment> paginatedComments;
		
		hilo = findOne(valueOf);
		numberRows = p*10;
		paginatedComments = new ArrayList<Comment>();
		
		for(int i=numberRows-10; i<=hilo.getComments().size()-1; i++){
			if(i < numberRows){
				System.out.println((Comment) hilo.getComments().toArray()[i]);
				paginatedComments.add((Comment) hilo.getComments().toArray()[i]);
			}else{
				break;
			}
		}
		return paginatedComments;
		
	}
	
	public Integer calculateLastPage(Comment comment, domain.Thread hilo){
		Double numberComments;
		Double pageDouble;
		Integer res;
		
		if(comment != null)
			numberComments = (double) comment.getThread().getComments().size()+1;
		else
			numberComments = (double) hilo.getComments().size();
		// Calcula qué pagina corresponde al mensaje recién creado, en decimal
		pageDouble = numberComments/10;
		// Se le aplica un redondeo siempre hacia arriba para el cálculo final de la página

		res = (int) Math.ceil(pageDouble);
		
		return res;
		
	}
}