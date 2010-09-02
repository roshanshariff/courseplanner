package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.springframework.stereotype.Component;

import ca.ualberta.cs.courseplanner.entities.User;
import ca.ualberta.cs.courseplanner.server.services.DataRepository;
import ca.ualberta.cs.courseplanner.server.services.UserIdProvider;


public class CurrentUserProvider implements Provider<User> {
	
	private final UserIdProvider userIdProvider;
	
	private final DataRepository dataRepository;

	@Inject
	public CurrentUserProvider (UserIdProvider userIdProvider, DataRepository dataRepository) {
		this.userIdProvider = userIdProvider;
		this.dataRepository = dataRepository;
	}

	@Override
	public User get () {
		return dataRepository.getUser(userIdProvider.getUserId());
	}

}
