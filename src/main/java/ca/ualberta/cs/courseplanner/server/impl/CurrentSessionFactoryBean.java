package ca.ualberta.cs.courseplanner.server.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;


@Component
@Named("hibernateSession")
public class CurrentSessionFactoryBean extends AbstractFactoryBean<Session> {
	
	private final SessionFactory sessionFactory;

	@Inject
	public CurrentSessionFactoryBean (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		setSingleton(false);
	}

	@Override
	protected Session createInstance () throws Exception {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Class<?> getObjectType () {
		return Session.class;
	}

}
