package ca.ualberta.cs.courseplanner.server.services;

import ca.ualberta.cs.courseplanner.model.CourseSearchResults;


public interface SearchEngine {
	
	CourseSearchResults searchCourses (String query, int firstResult, int maxResults);

}
