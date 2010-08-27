package ca.ualberta.cs.courseplanner.model;


public class CourseDetails extends CourseInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -2631723269549995174L;

	
	private String subjectName;

	private String org1Name;
	private String org2Name;

	private String description;

	
	public String getSubjectName () { return subjectName; }

	public void setSubjectName (String subjectName) { this.subjectName = subjectName; }
	
	public String getOrg1Name () { return org1Name; }
	
	public void setOrg1Name (String org1Name) { this.org1Name = org1Name; }
	
	public String getOrg2Name () { return org2Name; }

	public void setOrg2Name (String org2Name) { this.org2Name = org2Name; }
	
	public String getDescription () { return description; }
	
	public void setDescription (String description) { this.description = description; }

}
