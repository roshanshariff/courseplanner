package ca.ualberta.cs.courseplanner.server;

import ca.ualberta.cs.courseplanner.model.CourseSearchResults;


public interface SearchEngine {
	
	CourseSearchResults searchCourses (String query, int offset, int results);

}
