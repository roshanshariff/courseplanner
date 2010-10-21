package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

import ca.ualberta.cs.courseplanner.entities.User;
import ca.ualberta.cs.courseplanner.server.services.DataRepository;


@Component
@Named("currentUser")
public class CurrentUserFactoryBean extends AbstractFactoryBean<User> {
	
	private final Provider<String> currentUserId;
	
	private final DataRepository dataRepository;

	@Inject
	public CurrentUserFactoryBean (DataRepository dataRepository,
			@Named("currentUserId") Provider<String> userIdProvider) {
		this.currentUserId = userIdProvider;
		this.dataRepository = dataRepository;
		setSingleton(false);
	}

	@Override
	protected User createInstance () throws Exception {
		return dataRepository.getUser(currentUserId.get());
	}

	@Override
	public Class<?> getObjectType () {
		return User.class;
	}

}
