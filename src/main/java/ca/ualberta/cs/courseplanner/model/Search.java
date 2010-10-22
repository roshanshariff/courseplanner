package ca.ualberta.cs.courseplanner.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class Search implements java.io.Serializable {

	private static final long serialVersionUID = 8828926624038773195L;

	private String query;

	private Ordering ordering;

	
	public Search () { }
	
	public Search (String query, Ordering ordering) {
		this.query = query;
		this.ordering = ordering;
	}
	
	
	public String getQuery () {
		return query;
	}

	public void setQuery (String query) {
		this.query = query;
	}

	@Enumerated(EnumType.STRING)
	@Column(length=8)
	public Ordering getOrdering () {
		return ordering;
	}

	public void setOrdering (Ordering ordering) {
		this.ordering = ordering;
	}


	public static enum Ordering {

		DEFAULT  ("d"),		// order by relevance, subject, number, id
		SUBJECT  ("s"),		// sort by subject, number, id
		LEVEL    ("l"),		// sort by level, subject, number, id
		LEVELREV ("lr");	// sort by level reversed, subject, number, id

		private final String shortName;

		private Ordering (String shortName) {
			this.shortName = shortName;
		}

		public String getShortName () {
			return shortName;
		}

		public static Ordering fromShortName (String shortName) {
			for (Ordering ordering : values()) {
				if (ordering.getShortName().equals(shortName)) {
					return ordering;
				}
			}
			return DEFAULT;
		}

	}

}
