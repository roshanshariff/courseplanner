package ca.ualberta.cs.courseplanner.domain;

import javax.persistence.*;
import org.hibernate.search.annotations.*;

@Entity
@Table(name="courses")
@Indexed
public class Course implements java.io.Serializable {
	
	private static final long serialVersionUID = 1759624274109532278L;

	private Long id;
	
	private Subject subject;
	private String number;
	
	private String title;
	
	private Organisation org1;
	private Organisation org2;
	
	private Career career;
	
	private String description;
	
	@Id
	@DocumentId
	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	@ManyToOne
	@IndexedEmbedded
	public Subject getSubject () {
		return subject;
	}

	public void setSubject (Subject subject) {
		this.subject = subject;
	}
	
	@Column(length=4)
	@Field(index=Index.UN_TOKENIZED, store=Store.YES)
	public String getNumber () {
		return number;
	}
	
	public void setNumber (String number) {
		this.number = number;
	}
	
	@Column(length=200)
	@Field(index=Index.TOKENIZED, store=Store.YES)
	public String getTitle () {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}

	@ManyToOne
	@IndexedEmbedded
	public Organisation getOrg1 () {
		return org1;
	}
	
	public void setOrg1 (Organisation org1) {
		this.org1 = org1;
	}

	@ManyToOne
	@IndexedEmbedded
	public Organisation getOrg2 () {
		return org2;
	}

	public void setOrg2 (Organisation org2) {
		this.org2 = org2;
	}

	@Enumerated(EnumType.STRING)
	@Column(length=4)
	public Career getCareer () {
		return career;
	}

	public void setCareer (Career career) {
		this.career = career;
	}

	@Lob
	@Field(index=Index.TOKENIZED, store=Store.NO)
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}

}
