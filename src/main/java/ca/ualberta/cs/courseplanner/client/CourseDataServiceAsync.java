package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CourseDataServiceAsync {

	void getHello (AsyncCallback<String> callback);

	void getCourseDetails (String id, AsyncCallback<CourseDetails> callback);

	void getCourse (String id, AsyncCallback<Course> callback);

}
