package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.client.CourseDataService;
import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.domain.Subject;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class CourseDataServiceImpl extends RemoteServiceServlet implements
CourseDataService {

	private static final long serialVersionUID = 6889481816872990856L;

	private CourseData courseData;
	
	public void setCourseData (CourseData courseData) {
		this.courseData = courseData;
	}
	
	@Override
	public String getHello () {
		List<Subject> subjects = courseData.getSubjects();
		return subjects.get(0).getId();
	}

	@Override
	public CourseDetails getCourseDetails (String id) {
		return courseData.getCourseDetails(Long.parseLong(id));
	}

	@Override
	public Course getCourse (String id) {
		return courseData.getCourse(Long.parseLong(id));
	}

}
