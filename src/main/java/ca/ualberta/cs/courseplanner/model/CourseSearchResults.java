package ca.ualberta.cs.courseplanner.model;

import java.util.List;


public class CourseSearchResults implements java.io.Serializable {
	
	private static final long serialVersionUID = 8316357816735031921L;
	
	
	private String queryString;

	private int firstResult;
	
	private int numResults;
	
	private boolean numResultsExact;
	
	private CourseInfo[] results;

	
	public String getQueryString () {
		return queryString;
	}

	
	public void setQueryString (String queryString) {
		this.queryString = queryString;
	}

	
	public int getFirstResult () {
		return firstResult;
	}

	
	public void setFirstResult (int firstResult) {
		this.firstResult = firstResult;
	}

	
	public int getNumResults () {
		return numResults;
	}

	
	public void setNumResults (int numResults) {
		this.numResults = numResults;
	}

	
	public boolean isNumResultsExact () {
		return numResultsExact;
	}

	
	public void setNumResultsExact (boolean numResultsExact) {
		this.numResultsExact = numResultsExact;
	}

	
	public CourseInfo[] getResults () {
		return results;
	}

	
	public void setResults (CourseInfo[] results) {
		this.results = results;
	}

	
	public static long getSerialversionuid () {
		return serialVersionUID;
	}
	
}
