package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import domain.Comment;
import domain.Rating;
import domain.Thread;
import domain.User;
import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class UserService {

	// Managed repository
	@Autowired
	private UserRepository userRepository;

	// Supporting services

	// Constructors
	public UserService() {
		super();
	}

	// Simple CRUD methods
	public User create(String username) {
		User res;
		UserAccount userAccount;
		Authority authority;
		List<Thread> threads;
		List<Comment> comments;
		List<Rating> ratings;

		res = new User();
		userAccount = new UserAccount();
		Collection<Authority> authorities = new ArrayList<Authority>();
		authority = new Authority();
		threads = new ArrayList<Thread>();
		comments = new ArrayList<Comment>();
		ratings = new ArrayList<Rating>();

		authority.setAuthority("USER");
		authorities.add(authority);
		userAccount.setUsername(username);
		userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
		userAccount.setAuthorities(authorities);

		res.setName(username);
		res.setUserAccount(userAccount);
		res.setComments(comments);
		res.setThreads(threads);
		res.setRatings(ratings);
		return res;
	}

	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	// Other business methods

	public User findOneByPrincipal() {
		User res = new User();
		res = userRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		return res;
	}

	public User findByUsername(String username) {
		User res = new User();
		res = userRepository.findByUsername(username);
		return res;
	}

	public Collection<User> findUserWithZeroComments() {
		Collection<User> res = new ArrayList<User>();
		res = userRepository.findUserWithZeroComments();
		return res;
	}

	public Collection<User> findUserWithMoreComments() {
		Collection<User> res = new ArrayList<User>();
		res = userRepository.findUserWithMoreComments();
		return res;
	}

	public Collection<User> findUserWithMoreThreads() {
		Collection<User> res = new ArrayList<User>();
		res = userRepository.findUserWithMoreThreads();
		return res;
	}

}