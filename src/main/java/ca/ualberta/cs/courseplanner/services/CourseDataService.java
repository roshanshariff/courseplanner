package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.dto.CourseDetails;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("coursedata")
public interface CourseDataService extends RemoteService {
	
	public CourseDetails getCourseDetails (Long id);
	
}