package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;


public class CurrentSessionProvider implements Provider<Session> {
	
	private final SessionFactory sessionFactory;

	@Inject
	public CurrentSessionProvider (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Session get () {
		return sessionFactory.getCurrentSession();
	}

}
