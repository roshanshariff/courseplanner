package ca.ualberta.cs.courseplanner.server.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.*;
import ca.ualberta.cs.courseplanner.server.services.DataRepository;

@Repository
@Singleton
public class DataRepositoryImpl implements DataRepository {

	private final Provider<Session> session;
	
	@Inject
	public DataRepositoryImpl (SessionFactory sessionFactory) {
		this.session = new CurrentSessionProvider(sessionFactory);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Course getCourse (Long id) {
		return (Course)session.get().load(Course.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Subject> getSubjects () {
		@SuppressWarnings("unchecked")
		List<Subject> result = session.get().createCriteria(Subject.class).list();
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Organisation> getOrganisations () {
		@SuppressWarnings("unchecked")
		List<Organisation> result = session.get().createCriteria(Organisation.class).list();
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public User getUser(String id) {
		return (User)session.get().load(User.class, id);
	}
	
	@Override
	@Transactional
	public Plan createPlan (User user, String name) {
		Plan plan = new Plan();
		plan.setUser(user);
		plan.setName(name);
		session.get().save(plan);
		session.get().flush();
		return plan;
	}

	@Override
	@Transactional(readOnly=true)
	public Plan getPlan(Long id) {
		return (Plan)session.get().load(Plan.class, id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Plan> getUserPlans (String userId) {
		@SuppressWarnings("unchecked")
		List<Plan> result = session.get()
			.createCriteria(Plan.class)
			.createCriteria("user")
				.add(Restrictions.idEq(userId))
			.list();
		return result;
	}

	@Override
	@Transactional
	public void deletePlan (Plan plan) {
		session.get().delete(plan);
	}
	
}
