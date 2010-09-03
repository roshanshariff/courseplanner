package ca.ualberta.cs.courseplanner.client.search;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;

import com.google.gwt.app.place.Place;


public class SavedSearchPlace extends Place {

	private final SavedSearchInfo search;
	
	private final int firstResult;
	
	private final CourseInfo course;

	public SavedSearchPlace (SavedSearchInfo search, int firstResult, CourseInfo course) {
		this.search = search;
		this.firstResult = firstResult;
		this.course = course;
	}

	public SavedSearchInfo getSearch () {
		return search;
	}
	
	public int getFirstResult () {
		return firstResult;
	}
	
	public CourseInfo getCourse () {
		return course;
	}
	
}
