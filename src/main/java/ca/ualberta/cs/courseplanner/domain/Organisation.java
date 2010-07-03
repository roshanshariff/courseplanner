package ca.ualberta.cs.courseplanner.domain;

import javax.persistence.*;

@Entity
@Table(name="organisations")
public class Organisation implements java.io.Serializable {
	
	private static final long serialVersionUID = -1761496933032917072L;

	private String id;
	private String name;
	
	@Id
	@Column(length=10)
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
