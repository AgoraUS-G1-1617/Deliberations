
package domain;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


@Entity
@Access(AccessType.PROPERTY)
@Table(indexes={ @Index(columnList= "minThreads, minComments, minRatings" ) })
public class Rank extends DomainEntity{
	
	// Constructors ------------------------------------------------------------
	
	public Rank() {
		super();
	}
	

	// Attributes -------------------------------------------------------------
	
	private String title;
	private Integer number;
	private String descriptionEs;
	private String descriptionEn;
	private String icon;
	private Integer minThreads;
	private Integer minComments;
	private Integer minRatings;

	
	@NotNull
	@Range(min=0,max=5)
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	@Column(columnDefinition = "LONGBLOB") 
	public String getDescriptionEs() {
		return descriptionEs;
	}
	public void setDescriptionEs(String descriptionEs) {
		this.descriptionEs = descriptionEs;
	}
	
	@NotBlank
	@Column(columnDefinition = "LONGBLOB") 
	public String getDescriptionEn() {
		return descriptionEn;
	}
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	
	@NotNull
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@NotNull
	@Range(min=0,max=100)
	public Integer getMinThreads() {
		return minThreads;
	}
	public void setMinThreads(Integer minThreads) {
		this.minThreads = minThreads;
	}
	
	@NotNull
	@Range(min=0,max=100)
	public Integer getMinComments() {
		return minComments;
	}
	
	public void setMinComments(Integer minComments) {
		this.minComments = minComments;
	}
	
	@NotNull
	@Range(min=0,max=100)
	public Integer getMinRatings() {
		return minRatings;
	}
	public void setMinRatings(Integer minRatings) {
		this.minRatings = minRatings;
	}
	
	
	
	// Relationships ----------------------------------------------------------


}