package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.model.CourseDetails;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("../services/coursedata")
public interface CourseDataService extends RemoteService {
	
	public CourseDetails getCourseDetails (Long id);
	
}
