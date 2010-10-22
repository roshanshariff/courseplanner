package ca.ualberta.cs.courseplanner.client.course;

import java.util.HashMap;
import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.util.EscapeUtils;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;


public class CoursePlace extends Place {
	
	private final CourseInfo course;
	
	public CoursePlace (CourseInfo course) {
		this.course = course;
	}
	
	public CourseInfo getCourse () {
		return course;
	}
	
	
	@Prefix("course")
	public static class Tokenizer implements PlaceTokenizer<CoursePlace> {
		
		private static final String DEFAULT_KEY = "id";
		private static final String SUBJECT_KEY = "s";
		private static final String NUMBER_KEY = "n";
		private static final String NAME_KEY = "t";

		@Override
		public CoursePlace getPlace (String token) {
			
			Map<String, String> entries = EscapeUtils.decodeHistoryToken(DEFAULT_KEY, token);
			if (entries.isEmpty()) return null;
			
			CourseInfo course = new CourseInfo();
			try {
				course.setId(Long.parseLong(entries.get(DEFAULT_KEY)));
				course.setSubjectId(entries.get(SUBJECT_KEY));
				course.setNumber(entries.get(NUMBER_KEY));
				course.setName(entries.get(NAME_KEY));
			}
			catch (NumberFormatException e) {
				return null;
			}

			return new CoursePlace (course);
		}

		@Override
		public String getToken (CoursePlace place) {
			
			CourseInfo course = place.getCourse();
			
			Map<String, String> entries = new HashMap<String, String>();
			entries.put(DEFAULT_KEY, Long.toString(course.getId()));
			if (course.getSubjectId() != null) entries.put(SUBJECT_KEY, course.getSubjectId());
			if (course.getNumber() != null) entries.put(NUMBER_KEY, course.getNumber());
			
			return EscapeUtils.encodeHistoryToken(DEFAULT_KEY, entries);
		}
		
	}

}
