package ca.ualberta.cs.courseplanner.domain;

import javax.persistence.*;

@Entity
@Table(name="subjects")
public class Subject implements java.io.Serializable {

	private static final long serialVersionUID = 3873335230038902640L;

	private String id;
	private String name;
	
	@Id
	@Column(length=5)
	public String getId () {
		return id;
	}
	
	public void setId (String id) {
		this.id = id;
	}
	
	@Column(length=100)
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}

}
