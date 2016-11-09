package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	
	// Constructors ------------------------------------------------------------
	public Comment() {
		super();
	}

	
	// Attributes -------------------------------------------------------------
	private String text;
	private Date creationMoment;
	private Boolean erase;
		
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	
	@NotBlank
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	public Boolean getErase() {
		return erase;
	}
	public void setErase(Boolean erase) {
		this.erase = erase;
	}

	// Relationships ----------------------------------------------------------
	private User user;
	private Thread thread;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	
}