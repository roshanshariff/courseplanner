package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import org.dozer.Mapper;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.domain.*;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;


public class CourseDataImpl implements CourseData {

	private SessionFactory sessionFactory;
	
	private Mapper mapper;
	
	public void setSessionFactory (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setMapper (Mapper mapper) {
		this.mapper = mapper;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Subject> getSubjects () {
		return sessionFactory.getCurrentSession()
		.createCriteria(Subject.class).list();
	}

	@Override
	@Transactional(readOnly=true)
	public Course getCourse (Long id) {
		return (Course)sessionFactory.getCurrentSession().load(Course.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public CourseDetails getCourseDetails (Long id) {
		return mapper.map(getCourse(id), CourseDetails.class);
	}

}
