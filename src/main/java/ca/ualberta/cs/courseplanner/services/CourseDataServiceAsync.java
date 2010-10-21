package ca.ualberta.cs.courseplanner.services;

import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.SearchResults;
import ca.ualberta.cs.courseplanner.model.SearchOrdering;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CourseDataServiceAsync {

	void getCourseDetails (Long id, AsyncCallback<CourseDetails> callback);

	void searchCourses (String query, SearchOrdering ordering, int offset,
			int results, AsyncCallback<SearchResults> callback);

}
