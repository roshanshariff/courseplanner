package ca.ualberta.cs.courseplanner.server;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.services.CourseDataService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CourseDataServlet extends RemoteServiceServlet implements CourseDataService {
	
	private static final long serialVersionUID = -3955349790234109774L;

	private CourseDataService service;
	
	public void setService (CourseDataService service) {
		this.service = service;
	}

	@Override
	public CourseDetails getCourseDetails(Long id) {
		return service.getCourseDetails(id);
	}

}
