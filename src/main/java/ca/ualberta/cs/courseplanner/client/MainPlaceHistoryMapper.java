package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.course.CoursePlace;
import ca.ualberta.cs.courseplanner.client.search.place.SavedSearchPlace;
import ca.ualberta.cs.courseplanner.client.search.place.SearchQueryPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
	CoursePlace.Tokenizer.class,
	SearchQueryPlace.Tokenizer.class,
	SavedSearchPlace.Tokenizer.class
})
public interface MainPlaceHistoryMapper extends PlaceHistoryMapper { }
