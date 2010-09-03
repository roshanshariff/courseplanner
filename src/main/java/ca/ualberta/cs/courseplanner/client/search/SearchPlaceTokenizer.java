package ca.ualberta.cs.courseplanner.client.search;

import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.util.EscapeUtils;

import com.google.gwt.app.place.PlaceTokenizer;
import com.google.gwt.app.place.Prefix;
import com.google.gwt.dev.util.collect.HashMap;

@Prefix("search")
public class SearchPlaceTokenizer implements PlaceTokenizer<SearchPlace> {
	
	private static final String DEFAULT_KEY = "search";

	private static final String RESULT_KEY = "result";

	private static final String COURSE_KEY = "course";

	@Override
	public SearchPlace getPlace (String token) {
		
		Map<String, String> entries = EscapeUtils.decodeHistoryToken(DEFAULT_KEY, token);
		if (entries.isEmpty()) return null;
		
		String queryString = entries.get(DEFAULT_KEY);
		
		int firstResult = 0;
		try {
			String strFirstResult = entries.get(RESULT_KEY);
			if (strFirstResult != null) {
				firstResult = Integer.parseInt(strFirstResult.trim());
			}
		}
		catch (NumberFormatException e) { }
		
		CourseInfo course = null;
		try {
			String strCourseId = entries.get(COURSE_KEY);
			if (strCourseId != null) {
				long courseId = Long.parseLong(strCourseId.trim());
				course = new CourseInfo();
				course.setId(courseId);
			}
		}
		catch (NumberFormatException e) { }
		
		return new SearchPlace(queryString, firstResult, course);
	}

	@Override
	public String getToken (SearchPlace searchPlace) {

		Map<String, String> entries = new HashMap<String, String>();
		entries.put(DEFAULT_KEY, searchPlace.getQueryString());
		
		if (searchPlace.getFirstResult() > 0) {
			entries.put(RESULT_KEY, Integer.toString(searchPlace.getFirstResult()));
		}
		
		if (searchPlace.getCourse() != null) {
			entries.put(COURSE_KEY, Long.toString(searchPlace.getCourse().getId()));
		}
		
		return EscapeUtils.encodeHistoryToken(DEFAULT_KEY, entries);
	}

}
