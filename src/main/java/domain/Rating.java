
package domain;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


@Entity
@Access(AccessType.PROPERTY)
public class Rating extends DomainEntity{
	
	// Constructors ------------------------------------------------------------
	
	public Rating() {
		super();
	}
	

	// Attributes -------------------------------------------------------------
	private Integer rate;
	
	@NotNull
	@Range(min=1,max=5)
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
	
	
	// Relationships ----------------------------------------------------------

	private User user;
	private Thread thread;
	
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
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}

}