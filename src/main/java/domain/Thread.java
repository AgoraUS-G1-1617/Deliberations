
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "hilo", indexes={ @Index(columnList= "title, erase" ) })
public class Thread extends DomainEntity {

	// Constructors ------------------------------------------------------------

	public Thread() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String title;
	private Date creationMoment;
	private String decription;
	private Boolean erase;
	private boolean closed;
	// Although it might seem irrelevant, the attribute 'rating' must be
	// explicitly declared so that Hibernate knows the name of the getter
	// it has to call.
	@SuppressWarnings("unused")
	private double rating;
	private Comment lastComment;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCreationMoment() {
		return creationMoment;
	}

	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}

	@NotBlank
	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	public Boolean getErase() {
		return erase;
	}

	public void setErase(Boolean erase) {
		this.erase = erase;
	}
	
	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	@Transient
	public double getRating() {
		double result;
		
		result = 0.;
		
		for(Rating r: ratings) {
			result += r.getRate();
		}
		
		result = result/ratings.size();
		
		return result;
	}
	
	@Transient
	public Comment getLastComment() {
		List<Comment> comments;
		Comment lastComment;
		
		comments = new ArrayList<Comment>(this.comments);
		lastComment = null;
		
		if(!comments.isEmpty()){
			lastComment = comments.get(0);
			for(Comment c : comments){
				if(lastComment.getCreationMoment().before(c.getCreationMoment())){
					lastComment = c;
				}
			}
			
		}
		
		this.lastComment = lastComment;
		
		return this.lastComment;
	}

	public void setLastComment(Comment lastComment) {
		this.lastComment = lastComment;
	}

	// Relationships ----------------------------------------------------------

	private User user;
	private Collection<Comment> comments;
	private Collection<Rating> ratings;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@NotNull
	@OneToMany(mappedBy = "thread")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@NotNull
	@OneToMany(mappedBy = "thread")
	public Collection<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Rating> ratings) {
		this.ratings = ratings;
	}

}