package ca.ualberta.cs.courseplanner.client.search;

import ca.ualberta.cs.courseplanner.model.CourseInfo;

import com.google.gwt.app.place.Place;


public class SearchPlace extends Place {
	
	private final String queryString;
	
	private final int firstResult;
	
	private final CourseInfo course;

	public SearchPlace (String queryString, int firstResult, CourseInfo course) {
		this.queryString = queryString;
		this.firstResult = firstResult;
		this.course = course;
	}

	public String getQueryString () {
		return queryString;
	}
	
	public int getFirstResult () {
		return firstResult;
	}
	
	public CourseInfo getCourse () {
		return course;
	}

}