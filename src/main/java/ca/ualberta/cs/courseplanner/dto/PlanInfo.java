package ca.ualberta.cs.courseplanner.dto;

public class PlanInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 9056869107744111310L;

	private Long id;
	
	private String name;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
