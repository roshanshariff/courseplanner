package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.stereotype.Component;


public class CurrentFullTextSessionProvider implements Provider<FullTextSession> {
	
	private final Provider<Session> session;

	@Inject
	public CurrentFullTextSessionProvider (Provider<Session> session) {
		this.session = session;
	}

	@Override
	public FullTextSession get () {
		return Search.getFullTextSession(session.get());
	}

}
