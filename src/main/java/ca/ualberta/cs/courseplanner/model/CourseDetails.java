package ca.ualberta.cs.courseplanner.model;


public class CourseDetails extends CourseInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -2631723269549995174L;

	
	private String subjectName;

	private String org1Id, org1Name;
	private String org2Id, org2Name;

	private Career career;
	
	private String description;

	
	public String getSubjectName () { return subjectName; }

	public void setSubjectName (String subjectName) { this.subjectName = subjectName; }
	
	public String getOrg1Name () { return org1Name; }
	
	public void setOrg1Name (String org1Name) { this.org1Name = org1Name; }
	
	public String getOrg2Name () { return org2Name; }

	public void setOrg2Name (String org2Name) { this.org2Name = org2Name; }
	
	public String getOrg1Id () { return org1Id; }

	public void setOrg1Id (String org1Id) { this.org1Id = org1Id; }

	public String getOrg2Id () { return org2Id; }

	public void setOrg2Id (String org2Id) {	this.org2Id = org2Id; }
	
	public Career getCareer () { return career; }

	public void setCareer (Career career) { this.career = career; }
	
	public String getDescription () { return description; }
	
	public void setDescription (String description) { this.description = description; }

}
