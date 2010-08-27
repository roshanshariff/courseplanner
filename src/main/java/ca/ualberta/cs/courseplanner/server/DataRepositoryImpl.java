package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.*;

@Repository
public class DataRepositoryImpl implements DataRepository {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession () {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Course getCourse (Long id) {
		return (Course)getSession().load(Course.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Subject> getSubjects () {
		@SuppressWarnings("unchecked")
		List<Subject> result = getSession().createCriteria(Subject.class).list();
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Organisation> getOrganisations () {
		@SuppressWarnings("unchecked")
		List<Organisation> result = getSession().createCriteria(Organisation.class).list();
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public User getUser(String id) {
		return (User)getSession().load(User.class, id);
	}
	
	@Override
	@Transactional
	public Plan createPlan (User user, String name) {
		Plan plan = new Plan();
		plan.setUser(user);
		plan.setName(name);
		getSession().save(plan);
		getSession().flush();
		return plan;
	}

	@Override
	@Transactional(readOnly=true)
	public Plan getPlan(Long id) {
		return (Plan)getSession().load(Plan.class, id);
	}
	
	@Override
	@Transactional
	public void deletePlan (Plan plan) {
		getSession().delete(plan);
	}

}
