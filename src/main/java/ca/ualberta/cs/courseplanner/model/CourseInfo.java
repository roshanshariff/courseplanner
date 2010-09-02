package ca.ualberta.cs.courseplanner.model;


public class CourseInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 6458859917613610677L;

	
	private long id;
	
	private String subjectId;
	private String number;
	
	private String name;
	
	
	public CourseInfo () { }
	
	public CourseInfo (long id, String subjectId, String number, String title) {
		this.id = id;
		this.subjectId = subjectId;
		this.number = number;
		this.name = title;
	}
	
	
	public long getId () { return id; }
	
	public void setId (long id) { this.id = id; }

	public String getSubjectId () { return subjectId; }
	
	public void setSubjectId (String subjectId) { this.subjectId = subjectId; }

	public String getNumber () { return number; }

	public void setNumber (String number) { this.number = number; }
	
	public String getName () { return name; }

	public void setName (String name) { this.name = name; }
	
}
