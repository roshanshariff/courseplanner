package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CourseSearchResults;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CourseDataServiceAsync {

	void getCourseDetails (Long id, AsyncCallback<CourseDetails> callback);

	void searchCourses (String query, int offset, int results,
			AsyncCallback<CourseSearchResults> callback);

}
