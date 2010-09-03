package ca.ualberta.cs.courseplanner.client.search;

import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;
import ca.ualberta.cs.courseplanner.util.EscapeUtils;

import com.google.gwt.app.place.PlaceTokenizer;
import com.google.gwt.app.place.Prefix;
import com.google.gwt.dev.util.collect.HashMap;

@Prefix("searchid")
public class SavedSearchPlaceTokenizer implements PlaceTokenizer<SavedSearchPlace> {
	
	private static final String DEFAULT_KEY = "searchid";

	private static final String RESULT_KEY = "result";

	private static final String COURSE_KEY = "course";

	@Override
	public SavedSearchPlace getPlace (String token) {
		
		Map<String, String> entries = EscapeUtils.decodeHistoryToken(DEFAULT_KEY, token);
		if (entries.isEmpty()) return null;
		
		SavedSearchInfo search = new SavedSearchInfo();
		try {
			search.setId(Long.parseLong(entries.get(DEFAULT_KEY)));
		}
		catch (NumberFormatException e) {
			return null;
		}
		
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
		
		return new SavedSearchPlace(search, firstResult, course);
	}

	@Override
	public String getToken (SavedSearchPlace searchPlace) {

		Map<String, String> entries = new HashMap<String, String>();
		entries.put(DEFAULT_KEY, Long.toString(searchPlace.getSearch().getId()));
		
		if (searchPlace.getFirstResult() > 0) {
			entries.put(RESULT_KEY, Integer.toString(searchPlace.getFirstResult()));
		}
		
		if (searchPlace.getCourse() != null) {
			entries.put(COURSE_KEY, Long.toString(searchPlace.getCourse().getId()));
		}
		
		return EscapeUtils.encodeHistoryToken(DEFAULT_KEY, entries);
	}

}
