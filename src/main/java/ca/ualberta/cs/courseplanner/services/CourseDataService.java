package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.SearchResults;
import ca.ualberta.cs.courseplanner.model.SearchOrdering;

import com.google.gwt.rpc.client.RpcService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("../services/coursedata")
public interface CourseDataService extends RpcService {
	
	CourseDetails getCourseDetails (Long id);
	
	SearchResults searchCourses (String query, SearchOrdering ordering, int offset, int results);
	
}
