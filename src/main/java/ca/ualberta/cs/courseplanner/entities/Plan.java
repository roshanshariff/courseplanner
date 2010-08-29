package ca.ualberta.cs.courseplanner.entities;

import java.util.Map;

import javax.persistence.*;

import ca.ualberta.cs.courseplanner.model.CoursePlanInfo;

@Entity
@Table(name="plans")
public class Plan {
	
	private Long id;
	
	private User user;
	
	private String name;
	
	private Map<Course, CoursePlanInfo> courses;
	
	@Id
	@GeneratedValue
	public Long getId () { return id; }
	
	public void setId (Long id) { this.id = id; }

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser () { return user; }

	public void setUser (User user) { this.user = user; }

	public String getName () { return name; }

	public void setName (String name) { this.name = name; }

	@ElementCollection
	@CollectionTable(name="plan_courses")
	public Map<Course, CoursePlanInfo> getCourses() { return courses; }

	public void setCourses(Map<Course, CoursePlanInfo> courses) { this.courses = courses; }

}
