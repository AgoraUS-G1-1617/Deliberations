package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Karma;
import domain.User;
import repositories.KarmaRepository;
import repositories.ThreadRepository;
import utilities.AbstractTest;
import utilities.UtilTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class KarmaTest extends AbstractTest{
	
	// Service to test --------------------------------------------------------
	
	@Autowired
	private KarmaService karmaService;
	
	
	// Supporting services ----------------------------------------------------	
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	
	// Supporting repositories ------------------------------------------------

	@Autowired
	private KarmaRepository karmaRepository;
	
	@Autowired
	private ThreadRepository threadRepository;
	
	
	
	// Test cases -------------------------------------------------------------	
	
	@Test
	public void test_setKarma_ok_valueUp(){
		Karma karma;
		String value;
		Comment comment;
		int preValue, id_comment, id_karma;
		
		authenticate("user2");
		
		id_comment = UtilTest.getIdFromBeanName("comment2");
		id_karma = UtilTest.getIdFromBeanName("karma3");
		
		comment = commentService.findOne(id_comment);
		karma = karmaService.findOne(id_karma);
		
		value = "up";
		preValue = karma.getValue();
		
		karma = karmaService.setKarma(karma, comment, value);
		
		karma = karmaService.save(karma);
		
		Assert.isTrue(karma.getComment() == comment);
		Assert.isTrue(karma.getValue() == preValue + 2); // el que le quité además del que le añado
	}
	
	
	@Test
	public void test_setKarma_ok_valueDown(){
		Karma karma;
		String value;
		Comment comment;
		int preValue, id_comment, id_karma;
		
		authenticate("user2");
		
		id_comment = UtilTest.getIdFromBeanName("comment2");
		id_karma = UtilTest.getIdFromBeanName("karma4");
		
		comment = commentService.findOne(id_comment);
		karma = karmaService.findOne(id_karma);
		
		
		value = "down";
		preValue = karma.getValue();
		
		karma = karmaService.setKarma(karma, comment, value);
		
		karma = karmaService.save(karma);
		
		Assert.isTrue(karma.getComment() == comment);
		Assert.isTrue(karma.getValue() == preValue - 2); // el que tenía además del que le quitó
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setKarma_error_invalidValue(){
		Karma karma;
		String value;
		Comment comment;
		int preValue, id_comment, id_karma;
		
		authenticate("user2");
		
		id_comment = UtilTest.getIdFromBeanName("comment2");
		id_karma = UtilTest.getIdFromBeanName("karma4");
		
		comment = commentService.findOne(id_comment);
		karma = karmaService.findOne(id_karma);		
		
		value = "random";
		preValue = karma.getValue();
		
		karma = karmaService.setKarma(karma, comment, value);		
		
		Assert.isTrue(karma.getValue() != preValue); // No debería haber cambiado
	}

	
	@Test
	public void test_karmaOfComment_ok_checkAll(){
		Map<Comment, Map<Boolean, Integer>> recopilatorio;
		int pos, neg;

		// Guardamos en el map el recuento del karma de todos los comentarios
		recopilatorio = this.karmaOfComment_map();
		
		authenticate("user2");	
		
		// Comprobamos que el karma sea correcto para todos los comentarios
		
		
		for(Comment c:commentService.findAll()){
			List<Integer> res;
			
			pos = 0;
			neg = 0;
			
			if(recopilatorio.containsKey(c) && recopilatorio.get(c).containsKey(true)){
				pos = recopilatorio.get(c).get(true);
			}

			if(recopilatorio.containsKey(c) && recopilatorio.get(c).containsKey(false)){
				neg = recopilatorio.get(c).get(false);
			}
			
			
			res = karmaService.karmaOfComment(c.getId());

			
			if(res.get(1) > 0 || pos > 0){
				Assert.isTrue(res.get(1) == pos,
					"Comentario " + c.toString() + " valor esperado:" + String.valueOf(pos)
					+ " Obtenido: " + res.get(1)); // karma positivo
			}
			if(res.get(2) < 0 || neg > 0){
				Assert.isTrue(res.get(2) == - neg,
					"Comentario " + c.toString() + " valor esperado: -" + String.valueOf(neg) 
					+ " Obtenido: " + res.get(2)); // karma negativo
			}
			Assert.isTrue(res.get(0) == res.get(1) + res.get(2)); // karma total
			
		}
	}
	
	
	@Test
	public void test_karmaOfUser_ok() {
		User u;
		List<Integer> res;
		int kar, pos, neg;
		
		authenticate("user2");

		u = userService.findOneByPrincipal();

		// Calculamos el karma de todos los comentarios del usuario

		kar = 0;
		pos = 0;
		neg = 0;

		for (Comment act : commentService.commentsOfUser(u.getId())) {
			List<Integer> actKarma;

			actKarma = karmaService.karmaOfComment(act.getId());

			kar += actKarma.get(0);
			pos += actKarma.get(1);
			neg += actKarma.get(2);
		}

		// Comprobamos que el método devuelve lo esperado

		res = karmaService.karmaOfUser(u.getId());

		Assert.isTrue(res.get(1) == pos, "Valor esperado:" + String.valueOf(pos) + 
				" Obtenido: " + res.get(1)); // karma positivo

		Assert.isTrue(res.get(2) == neg, "Valor esperado: -" + String.valueOf(neg) + 
				" Obtenido: " + res.get(2)); // karma negativo

		Assert.isTrue(res.get(0) == kar); // karma total

	}

	
	@Test
	public void test_karmaOfUserAtComment_ok_karmaExists(){
		Karma calc, res;
		
		calc = karmaService.findAll().iterator().next();
		
		authenticate(calc.getUser().getUserAccount().getUsername());
		
		res = karmaService.karmaOfUserAtComment(calc.getComment().getId());
		
		Assert.isTrue(res.getId() == calc.getId());
	}
	
	
	@Test
	public void test_karmaOfUserAtComment_ok_karmaNotExists(){
		Karma res;
		Comment c;
		
		c = commentService.findAll().iterator().next();
		
		authenticate(c.getUser().getUserAccount().getUsername());
		
		res = karmaService.karmaOfUserAtComment(c.getId());
		
		Assert.isTrue(res == null);
		
	}
	
	
	@Test
	public void test_karmaOfThread_ok_checkAll(){		
		int tam_page;
		
		tam_page = 10;
		
		for(domain.Thread t:threadRepository.findAll()){
			HashMap<Integer, List<Integer>> result;
			
			result = new HashMap<Integer, List<Integer>>();
			
			
			for(int n_page = 0;n_page < Integer.valueOf(t.getComments().size()/tam_page) + 1; n_page++){
				result.putAll(karmaService.karmaOfThread(t.getId(), n_page + 1));
			}
			
			// Check Size
			Assert.isTrue(result.size() == t.getComments().size(),
					"Expected " + t.getComments().size() + " but was " + result.size());
			
			for(Comment c:t.getComments()){
				List<Integer> karma_comment;
				
				karma_comment = karmaService.karmaOfComment(c.getId());
				
				// Check each comment
				Assert.isTrue(result.get(c.getId()).get(0) == karma_comment.get(0) &&
						result.get(c.getId()).get(1) == karma_comment.get(1) &&
						result.get(c.getId()).get(2) == karma_comment.get(2),
						"Unexpected karma for comment: " + String.valueOf(c.getId()));
			}
		}
	
		
	}
	
	
	// Ancillary Methods -----------------------------------------------
	
	/**
	 * This method return a map with the additions Karma Comments
	 * @return 
	 */
	private Map<Comment, Map<Boolean, Integer>> karmaOfComment_map(){
		Map<Comment, Map<Boolean, Integer>> result;
		
		result = new HashMap<Comment, Map<Boolean,Integer>>();

		for(Karma k:karmaRepository.findAll()){
			Map<Boolean, Integer> karma_comment;
			int count;

			if(result.containsKey(k.getComment())){
				karma_comment = result.get(k.getComment());
				if(karma_comment.containsKey(k.getValue() == 1)){
					count = karma_comment.get(k.getValue() == 1) + 1;
				}else{
					count = 1;
				}
			}else{
				karma_comment = new HashMap<Boolean,Integer>();	
				count = 1;
			}
			
			karma_comment.put(k.getValue() == 1, count);
			result.put(k.getComment(), karma_comment);
		}
		
		return result;
	}

}
