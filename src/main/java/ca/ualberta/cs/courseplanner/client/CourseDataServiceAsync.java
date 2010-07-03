package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CourseDataServiceAsync {

	void getCourseDetails (Long id, AsyncCallback<CourseDetails> callback);

}
