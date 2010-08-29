package ca.ualberta.cs.courseplanner.server;


public class UserIdProviderMockImpl implements UserIdProvider {
	
	private String userId;
	
	public void setUserId (String userId) {
		this.userId = userId;
	}

	@Override
	public String getUserId () {
		return userId;
	}

}
