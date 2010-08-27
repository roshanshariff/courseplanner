package ca.ualberta.cs.courseplanner.model;


public class CourseInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 6458859917613610677L;

	
	private long id;
	
	private String subjectId;
	private String number;
	
	private String title;
	
	private String org1Id;
	private String org2Id;
	
	private Career career;
	
	
	public long getId () { return id; }
	
	public void setId (long id) { this.id = id; }

	public String getSubjectId () { return subjectId; }
	
	public void setSubjectId (String subjectId) { this.subjectId = subjectId; }

	public String getNumber () { return number; }

	public void setNumber (String number) { this.number = number; }
	
	public String getTitle () { return title; }

	public void setTitle (String title) { this.title = title; }
	
	public String getOrg1Id () { return org1Id; }

	public void setOrg1Id (String org1Id) { this.org1Id = org1Id; }

	public String getOrg2Id () { return org2Id; }

	public void setOrg2Id (String org2Id) {	this.org2Id = org2Id; }
	
	public Career getCareer () { return career; }

	public void setCareer (Career career) { this.career = career; }
	
}
