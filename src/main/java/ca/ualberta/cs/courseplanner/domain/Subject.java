package ca.ualberta.cs.courseplanner.domain;

import javax.persistence.*;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name="subjects")
public class Subject implements java.io.Serializable {

	private static final long serialVersionUID = 3873335230038902640L;

	private String id;
	private String name;
	
	@Id
	@Column(length=5)
	@Field(index=Index.UN_TOKENIZED, store=Store.YES)
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
