package ca.ualberta.cs.courseplanner.entities;

import javax.persistence.*;

import ca.ualberta.cs.courseplanner.model.SearchOrdering;

@Entity
@Table(name="searches")
public class SavedSearch implements UserObject {
	
	private Long id;
	
	private User user;
	
	private String name;
	
	private String description;
	
	private boolean publicReadable;
	
	private String query;
	
	private SearchOrdering ordering;
	
	@Id
	@GeneratedValue
	public Long getId () { return id; }
	
	public void setId (Long id) { this.id = id; }

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser () { return user; }

	public void setUser (User user) { this.user = user; }

	@Column(length=256)
	public String getName () { return name; }

	public void setName (String name) { this.name = name; }
	
	@Lob
	public String getDescription () { return description; }
	
	public void setDescription (String description) { this.description = description; }
	
	public boolean isPublic () { return publicReadable; }
	
	public void setPublic (boolean publicReadable) { this.publicReadable = publicReadable; }

	public String getQuery () { return query; }
	
	public void setQuery (String query) { this.query = query; }
	
	@Enumerated(EnumType.STRING)
	@Column(length=8)
	public SearchOrdering getOrdering () { return ordering; }
	
	public void setOrdering (SearchOrdering ordering) { this.ordering = ordering; }
	
}
