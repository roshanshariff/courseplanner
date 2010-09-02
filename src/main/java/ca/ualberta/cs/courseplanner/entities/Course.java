package ca.ualberta.cs.courseplanner.entities;

import javax.persistence.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.cfg.ConcatStringBridge;

import ca.ualberta.cs.courseplanner.model.Career;
import ca.ualberta.cs.courseplanner.server.search.CourseBridge;
import ca.ualberta.cs.courseplanner.server.search.CourseOrgBridge;
import ca.ualberta.cs.courseplanner.server.search.CourseOrgIdBridge;
import ca.ualberta.cs.courseplanner.server.search.ObjectToStringBridge;

@Entity
@Table(name="courses")
@Indexed
@ClassBridges({
	@ClassBridge(name="all", index=Index.TOKENIZED, store=Store.NO, impl=CourseBridge.class),
	@ClassBridge(name="org", index=Index.TOKENIZED, store=Store.NO, impl=CourseOrgBridge.class),
	@ClassBridge(name="org.id", index=Index.UN_TOKENIZED, store=Store.NO, impl=CourseOrgIdBridge.class)
})
public class Course implements java.io.Serializable {
	
	private static final long serialVersionUID = 1759624274109532278L;

	private Long id;
	
	private Subject subject;
	private String number;
	
	private String name;
	
	private Organisation org1;
	private Organisation org2;
	
	private Career career;
	
	private String description;
	
	@Id
	@DocumentId
	public Long getId () { return id; }

	public void setId (Long id) { this.id = id; }

	@ManyToOne
	@IndexedEmbedded
	@Field(store=Store.NO, index=Index.TOKENIZED, bridge=@FieldBridge(impl=ObjectToStringBridge.class))
	public Subject getSubject () { return subject; }

	public void setSubject (Subject subject) { this.subject = subject; }
	
	@Column(length=4)
	@Fields({
		@Field(index=Index.UN_TOKENIZED, store=Store.YES),
		@Field(name="level", index=Index.UN_TOKENIZED,
				bridge=@FieldBridge(impl=ConcatStringBridge.class,
									params=@Parameter(name="size", value="1")))
	})
	public String getNumber () { return number; }
	
	public void setNumber (String number) { this.number = number; }
	
	@Column(length=200)
	@Field(index=Index.TOKENIZED, store=Store.YES)
	public String getName () { return name; }
	
	public void setName (String name) { this.name = name; }

	@ManyToOne
	@IndexedEmbedded
	public Organisation getOrg1 () { return org1; }
	
	public void setOrg1 (Organisation org1) { this.org1 = org1; }

	@ManyToOne
	@IndexedEmbedded
	public Organisation getOrg2 () { return org2; }

	public void setOrg2 (Organisation org2) { this.org2 = org2; }

	@Enumerated(EnumType.STRING)
	@Column(length=4)
	public Career getCareer () { return career; }

	public void setCareer (Career career) { this.career = career; }

	@Lob
	@Field(index=Index.TOKENIZED, store=Store.NO)
	public String getDescription () { return description; }
	
	public void setDescription (String description) { this.description = description; }

}
