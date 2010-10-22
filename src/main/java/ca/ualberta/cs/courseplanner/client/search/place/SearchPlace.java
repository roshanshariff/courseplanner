package ca.ualberta.cs.courseplanner.client.search.place;

import com.google.gwt.place.shared.Place;

import ca.ualberta.cs.courseplanner.model.CourseInfo;


public abstract class SearchPlace extends Place {
	
	private final int firstResult;
	
	public SearchPlace (int firstResult) {
		this.firstResult = firstResult;
	}
	
	public int getFirstResult () {
		return firstResult;
	}
	
}
