package ca.ualberta.cs.courseplanner.entities;

import java.util.*;

import javax.persistence.*;

import ca.ualberta.cs.courseplanner.model.CourseHistoryInfo;

@Entity
@Table(name="users")
public class User {
	
	public static enum Role {
		STUDENT, ADMIN;
	}
	
	private String id;
	private String password;
	
	private Set<Role> roles = EnumSet.noneOf(Role.class);
	
	private boolean enabled = true;
	private boolean locked = false;
	
	private Map<Course, CourseHistoryInfo> courseHistory;
	
	private Set<Plan> plans;

	@Id
	@Column(length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length=128)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="user_roles")
	@Column(length=8)
	public Set<User.Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<User.Role> roles) {
		this.roles = roles;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@ElementCollection
	@CollectionTable(name="user_coursehistory")
	public Map<Course, CourseHistoryInfo> getCourseHistory() {
		return courseHistory;
	}

	public void setCourseHistory(Map<Course, CourseHistoryInfo> courseHistory) {
		this.courseHistory = courseHistory;
	}

	@OneToMany(mappedBy="user")
	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

}
