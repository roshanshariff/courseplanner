package ca.ualberta.cs.courseplanner.dto;

public class PlanInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 9056869107744111310L;

	private long id;
	
	private String name;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
