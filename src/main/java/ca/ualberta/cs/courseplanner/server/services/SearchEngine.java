package ca.ualberta.cs.courseplanner.server.services;

import ca.ualberta.cs.courseplanner.model.SearchResults;
import ca.ualberta.cs.courseplanner.model.SearchOrdering;


public interface SearchEngine {
	
	SearchResults searchCourses (String query, SearchOrdering ordering, int firstResult, int maxResults);

}
