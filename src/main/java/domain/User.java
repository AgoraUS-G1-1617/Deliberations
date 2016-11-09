
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class User extends DomainEntity {

	// Constructors ------------------------------------------------------------

	public User() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private String name;
	private String surname;
	private String email;
	private String autonomous_community;
	private String genre;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAutonomous_community() {
		return autonomous_community;
	}

	public void setAutonomous_community(String autonomous_community) {
		this.autonomous_community = autonomous_community;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	// Relationships ----------------------------------------------------------

	UserAccount userAccount;
	private Collection<Comment> comments;
	private Collection<Thread> threads;
	private Collection<Rating> ratings;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Thread> getThreads() {
		return threads;
	}

	public void setThreads(Collection<Thread> threads) {
		this.threads = threads;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Rating> ratings) {
		this.ratings = ratings;
	}

}