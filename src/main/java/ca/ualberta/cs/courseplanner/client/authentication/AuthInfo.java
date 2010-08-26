package ca.ualberta.cs.courseplanner.client.authentication;

public interface AuthInfo {
	
	boolean isAuthenticated ();
	
	boolean isAdmin ();
	
	String getUserId ();
	
	String getPassword ();
	
	String getEffectiveUserId ();

}
