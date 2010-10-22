package ca.ualberta.cs.courseplanner.client.search.place;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;
import ca.ualberta.cs.courseplanner.util.EscapeUtils;


public class SavedSearchPlace extends SearchPlace {

	private final SavedSearchInfo savedSearch;
	
	public SavedSearchPlace (SavedSearchInfo savedSearch, int firstResult) {
		super (firstResult);
		this.savedSearch = savedSearch;
	}

	public SavedSearchInfo getSavedSearch () {
		return savedSearch;
	}
	
	
	@Prefix("searchid")
	public static class Tokenizer implements PlaceTokenizer<SavedSearchPlace> {
		
		private static final String DEFAULT_KEY = "id";
		private static final String NAME_KEY = "n";
		private static final String RESULT_KEY = "i";

		@Override
		public SavedSearchPlace getPlace (String token) {
			
			Map<String, String> entries = EscapeUtils.decodeHistoryToken(DEFAULT_KEY, token);
			if (entries.isEmpty()) return null;
			
			SavedSearchInfo savedSearch = new SavedSearchInfo();
			try {
				savedSearch.setId(Long.parseLong(entries.get(DEFAULT_KEY)));
				savedSearch.setName(entries.get(NAME_KEY));
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
			
			return new SavedSearchPlace(savedSearch, firstResult);
		}

		@Override
		public String getToken (SavedSearchPlace searchPlace) {

			Map<String, String> entries = new HashMap<String, String>();
			
			entries.put(DEFAULT_KEY, Long.toString(searchPlace.getSavedSearch().getId()));
			
			if (searchPlace.getSavedSearch().getName() != null) {
				entries.put(NAME_KEY, searchPlace.getSavedSearch().getName());
			}
			
			if (searchPlace.getFirstResult() > 0) {
				entries.put(RESULT_KEY, Integer.toString(searchPlace.getFirstResult()));
			}
			
			return EscapeUtils.encodeHistoryToken(DEFAULT_KEY, entries);
		}

	}

	
}
