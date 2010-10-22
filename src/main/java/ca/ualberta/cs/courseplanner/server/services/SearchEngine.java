package ca.ualberta.cs.courseplanner.server.services;

import ca.ualberta.cs.courseplanner.model.Search;
import ca.ualberta.cs.courseplanner.model.SearchResults;


public interface SearchEngine {
	
	SearchResults searchCourses (Search search, int firstResult, int maxResults);

}
