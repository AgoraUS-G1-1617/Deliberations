package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Karma extends DomainEntity {

	// Constructors ------------------------------------------------------------
	
	public Karma() {
		super();
	}

	// Attributes -------------------------------------------------------------
	
	private int value;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int rate) {
		this.value = rate;
	}
	
	// Relationships ----------------------------------------------------------

	private User user;
	private Comment comment;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Comment getComment() {
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}

}