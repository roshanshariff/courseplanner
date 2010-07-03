package ca.ualberta.cs.courseplanner.dto;

import ca.ualberta.cs.courseplanner.domain.*;

public class CourseDetails implements java.io.Serializable {
	
	private static final long serialVersionUID = -1751498711180009857L;

	private String id;
	
	private String subjectId, subjectName;
	private String number;
	
	private String title;
	
	private String org1Id, org1Name;
	private String org2Id, org2Name;
	
	private Career career;
	
	private String description;

	
	public String getId () {
		return id;
	}

	
	public void setId (String id) {
		this.id = id;
	}

	
	public String getSubjectId () {
		return subjectId;
	}

	
	public void setSubjectId (String subjectId) {
		this.subjectId = subjectId;
	}

	
	public String getSubjectName () {
		return subjectName;
	}

	
	public void setSubjectName (String subjectName) {
		this.subjectName = subjectName;
	}

	
	public String getNumber () {
		return number;
	}

	
	public void setNumber (String number) {
		this.number = number;
	}

	
	public String getTitle () {
		return title;
	}

	
	public void setTitle (String title) {
		this.title = title;
	}

	
	public String getOrg1Id () {
		return org1Id;
	}

	
	public void setOrg1Id (String org1Id) {
		this.org1Id = org1Id;
	}

	
	public String getOrg1Name () {
		return org1Name;
	}

	
	public void setOrg1Name (String org1Name) {
		this.org1Name = org1Name;
	}

	
	public String getOrg2Id () {
		return org2Id;
	}

	
	public void setOrg2Id (String org2Id) {
		this.org2Id = org2Id;
	}

	
	public String getOrg2Name () {
		return org2Name;
	}

	
	public void setOrg2Name (String org2Name) {
		this.org2Name = org2Name;
	}

	
	public Career getCareer () {
		return career;
	}

	
	public void setCareer (Career career) {
		this.career = career;
	}

	
	public String getDescription () {
		return description;
	}

	
	public void setDescription (String description) {
		this.description = description;
	}

}
