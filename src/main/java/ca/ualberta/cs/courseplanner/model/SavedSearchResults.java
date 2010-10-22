package ca.ualberta.cs.courseplanner.model;


public class SavedSearchResults extends SavedSearchDetails {

	private static final long serialVersionUID = -8761792132948744746L;
	
	
	private SearchResults results;
	
	
	public SearchResults getResults () { return results; }
	
	public void setResults (SearchResults results) { this.results = results; }

}
