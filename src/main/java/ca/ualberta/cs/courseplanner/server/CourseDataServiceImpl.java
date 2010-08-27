package ca.ualberta.cs.courseplanner.server;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.dto.*;
import ca.ualberta.cs.courseplanner.services.CourseDataService;


public class CourseDataServiceImpl implements CourseDataService {

	private DataRepository data;
	
	private Mapper mapper;
	
	public void setData (DataRepository data) {
		this.data = data;
	}
	
	public void setMapper (Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly=true)
	public CourseDetails getCourseDetails (Long id) {
		return mapper.map(data.getCourse(id), CourseDetails.class);
	}

}
