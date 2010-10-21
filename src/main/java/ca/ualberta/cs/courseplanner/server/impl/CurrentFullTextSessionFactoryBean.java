package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;


@Component
@Named("hibernateFullTextSession")
public class CurrentFullTextSessionFactoryBean extends AbstractFactoryBean<FullTextSession> {
	
	private final Provider<Session> session;

	@Inject
	public CurrentFullTextSessionFactoryBean (Provider<Session> session) {
		this.session = session;
		setSingleton(false);
	}

	@Override
	protected FullTextSession createInstance () throws Exception {
		return Search.getFullTextSession(session.get());
	}

	@Override
	public Class<?> getObjectType () {
		return FullTextSession.class;
	}

}
