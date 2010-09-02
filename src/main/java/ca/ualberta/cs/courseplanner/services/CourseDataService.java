package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CourseSearchResults;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("../services/coursedata")
public interface CourseDataService extends RemoteService {
	
	CourseDetails getCourseDetails (Long id);
	
	CourseSearchResults searchCourses (String query, int offset, int results);
	
}
