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

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserRepository userRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create(String username) {
		User result;
		UserAccount userAccount;
		Authority authority;
		List<Thread> threads;
		List<Comment> comments;
		List<Rating> ratings;
		Collection<Authority> authorities;

		result = new User();
		userAccount = new UserAccount();
		authorities = new ArrayList<Authority>();
		authority = new Authority();
		threads = new ArrayList<Thread>();
		comments = new ArrayList<Comment>();
		ratings = new ArrayList<Rating>();

		authority.setAuthority("USER");
		authorities.add(authority);
		userAccount.setUsername(username);
		userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
		userAccount.setAuthorities(authorities);

		result.setName(username);
		result.setUserAccount(userAccount);
		result.setComments(comments);
		result.setThreads(threads);
		result.setRatings(ratings);

		return result;
	}

	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	// Other business methods -------------------------------------------------

	public User findOneByPrincipal() {
		User result;

		result = new User();
		result = userRepository.findOneByPrincipal(LoginService.getPrincipal().getId());

		return result;
	}

	public User findByUsername(String username) {
		User result;

		result = new User();
		result = userRepository.findByUsername(username);

		return result;
	}

	public Collection<User> findUserWithZeroComments() {
		Collection<User> result = new ArrayList<User>();

		result = userRepository.findUserWithZeroComments();

		return result;
	}

	public Collection<User> findUserWithMoreComments() {
		Collection<User> result;

		result = new ArrayList<User>();
		result = userRepository.findUserWithMoreComments();

		return result;
	}

	public Collection<User> findUserWithMoreThreads() {
		Collection<User> result;

		result = new ArrayList<User>();
		result = userRepository.findUserWithMoreThreads();

		return result;
	}

}