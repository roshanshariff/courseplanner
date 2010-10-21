package ca.ualberta.cs.courseplanner.model;


public class PlanDetails extends PlanInfo {

	private static final long serialVersionUID = -6325550707839309400L;


	private String userId;
	
	private String description;
	
	private boolean publicReadable;
	
	
	public String getUserId () { return userId; }

	public void setUserId (String userId) { this.userId = userId; }

	public String getDescription () { return description; }

	public void setDescription (String description) { this.description = description; }
	
	public boolean isPublic () { return publicReadable; }

	public void setPublic (boolean publicReadable) { this.publicReadable = publicReadable; }

}
