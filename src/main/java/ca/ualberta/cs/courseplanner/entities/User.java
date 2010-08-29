package ca.ualberta.cs.courseplanner.entities;

import java.util.*;

import javax.persistence.*;

import ca.ualberta.cs.courseplanner.model.CourseTranscriptInfo;

@Entity
@Table(name="users")
public class User {
	
	public static enum Authority { STUDENT, ADMIN }
	
	private String id;
	private String password;
	
	private Set<Authority> authorities = EnumSet.noneOf(Authority.class);
	
	private boolean enabled = true;

	private Map<Course, CourseTranscriptInfo> transcript;
	
	private Set<Plan> plans;

	@Id
	@Column(length=64)
	public String getId () { return id; }

	public void setId (String id) { this.id = id; }

	@Column(length=128)
	public String getPassword () { return password; }

	public void setPassword (String password) { this.password = password; }

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="user_authorities")
	@Column(length=8)
	public Set<Authority> getAuthorities () { return authorities; }

	public void setAuthorities (Set<Authority> authorities) { this.authorities = authorities; }

	public boolean isEnabled () { return enabled; }

	public void setEnabled (boolean enabled) { this.enabled = enabled; }

	@ElementCollection
	@CollectionTable(name="user_transcript")
	public Map<Course, CourseTranscriptInfo> getCourseHistory() { return transcript; }

	public void setCourseHistory(Map<Course, CourseTranscriptInfo> courseHistory) { this.transcript = courseHistory; }

	@OneToMany(mappedBy="user")
	public Set<Plan> getPlans() { return plans; }

	public void setPlans (Set<Plan> plans) { this.plans = plans; }

}
