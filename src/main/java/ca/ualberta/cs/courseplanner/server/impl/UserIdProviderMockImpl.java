package ca.ualberta.cs.courseplanner.server.impl;

import ca.ualberta.cs.courseplanner.server.services.UserIdProvider;


public class UserIdProviderMockImpl implements UserIdProvider {
	
	private final String userId;
	
	public UserIdProviderMockImpl (String userId) {
		this.userId = userId;
	}

	@Override
	public String getUserId () {
		return userId;
	}

}
