package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.client.CourseDataService;
import ca.ualberta.cs.courseplanner.dto.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class CourseDataServiceImpl extends RemoteServiceServlet implements
CourseDataService {

	private static final long serialVersionUID = 6889481816872990856L;

	private CourseData courseData;
	
	public void setCourseData (CourseData courseData) {
		this.courseData = courseData;
	}
	
	@Override
	public CourseDetails getCourseDetails (Long id) {
		return courseData.getCourseDetails(id);
	}

}
