package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("coursedata")
public interface CourseDataService extends RemoteService {
	
	public String getHello ();
	
	public Course getCourse (String id);

	public CourseDetails getCourseDetails (String id);
	
}
