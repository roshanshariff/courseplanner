package ca.ualberta.cs.courseplanner.model;


public class SearchResults implements java.io.Serializable {
	
	private static final long serialVersionUID = 8316357816735031921L;
	
	
	private int firstResult;
	
	private int numResults;
	
	private boolean numResultsExact;
	
	private CourseInfo[] results;

	
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
