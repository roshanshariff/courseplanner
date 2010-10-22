package ca.ualberta.cs.courseplanner.model;


public class SavedSearchDetails extends SavedSearchInfo {

	private static final long serialVersionUID = 9102038657977503594L;
	
	
	private String userId;
	
	private String description;
	
	private boolean publicReadable;
	
	private Search search;
	
	
	public String getUserId () { return userId; }

	public void setUserId (String userId) { this.userId = userId; }

	public String getDescription () { return description; }

	public void setDescription (String description) { this.description = description; }
	
	public boolean isPublic () { return publicReadable; }

	public void setPublic (boolean publicReadable) { this.publicReadable = publicReadable; }

	public Search getSearch () { return search; }
	
	public void setSearch (Search search) { this.search = search; }
	
}
