package ca.ualberta.cs.courseplanner.domain;

import javax.persistence.*;

@Embeddable
public class CoursePlanInfo {
	
	private Semester semester;
	
	private Integer year;

	@Enumerated(EnumType.ORDINAL)
	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
