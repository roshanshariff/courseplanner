package ca.ualberta.cs.courseplanner.model;

import javax.persistence.*;

@Embeddable
public class CourseHistoryInfo implements java.io.Serializable {

	public static enum Type { TAKEN, CREDITED, WAIVED }
	
	private static final long serialVersionUID = 827047466038144913L;

	private Type type;
	
	@Enumerated(EnumType.STRING)
	@Column(length=8)
	public Type getType () { return type; }
	
	public void setType (Type type) { this.type = type; }
	
}
