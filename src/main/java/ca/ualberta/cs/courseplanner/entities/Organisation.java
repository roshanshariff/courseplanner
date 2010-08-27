package ca.ualberta.cs.courseplanner.entities;

import javax.persistence.*;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

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
	@Field(index=Index.TOKENIZED, store=Store.NO)
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
}
