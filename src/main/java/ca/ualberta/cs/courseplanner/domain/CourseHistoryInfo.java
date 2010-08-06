package ca.ualberta.cs.courseplanner.domain;

import javax.persistence.*;

@Embeddable
public class CourseHistoryInfo {

	public static enum Type {
		TAKEN, CREDITED, WAIVED
	}
	
	private Type type;
	
	@Enumerated(EnumType.STRING)
	@Column(length=8)
	public Type getType () {
		return type;
	}
	
	public void setType (Type type) {
		this.type = type;
	}
}
