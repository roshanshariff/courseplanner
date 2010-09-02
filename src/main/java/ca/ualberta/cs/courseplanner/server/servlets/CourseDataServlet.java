package ca.ualberta.cs.courseplanner.server.servlets;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CourseSearchResults;
import ca.ualberta.cs.courseplanner.services.CourseDataService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CourseDataServlet extends RemoteServiceServlet implements CourseDataService {
	
	private static final long serialVersionUID = -3955349790234109774L;

	private final CourseDataService service;
	
	public CourseDataServlet (CourseDataService service) {
		this.service = service;
	}
	
	@Override
	public CourseDetails getCourseDetails (Long id) {
		return service.getCourseDetails(id);
	}

	public CourseSearchResults searchCourses (String query, int offset, int results) {
		return service.searchCourses(query, offset, results);
	}

}
