package ca.ualberta.cs.courseplanner.model;

import javax.persistence.*;

@Embeddable
public class CoursePlanInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1641415994102952088L;

	
	private Semester semester;
	
	private Integer year;

	
	@Enumerated(EnumType.ORDINAL)
	public Semester getSemester() { return semester; }

	public void setSemester(Semester semester) { this.semester = semester; }

	public Integer getYear() { return year; }

	public void setYear(Integer year) { this.year = year; }

}
