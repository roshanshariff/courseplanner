package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.Search;
import ca.ualberta.cs.courseplanner.model.SearchResults;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CourseDataServiceAsync {

	void getCourseDetails (Long id, AsyncCallback<CourseDetails> callback);

	void searchCourses (Search search, int offset, int results,
			AsyncCallback<SearchResults> callback);

}
