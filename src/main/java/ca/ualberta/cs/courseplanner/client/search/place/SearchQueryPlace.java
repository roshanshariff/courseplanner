package ca.ualberta.cs.courseplanner.client.search.place;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import ca.ualberta.cs.courseplanner.model.Search;
import ca.ualberta.cs.courseplanner.util.EscapeUtils;


public class SearchQueryPlace extends SearchPlace {

	private final Search search;
	
	public SearchQueryPlace (Search search, int firstResult) {
		super (firstResult);
		this.search = search;
	}

	public Search getSearch () {
		return search;
	}

	
	@Prefix("search")
	public static class Tokenizer implements PlaceTokenizer<SearchQueryPlace> {
		
		private static final String DEFAULT_KEY = "q";
		private static final String ORDERING_KEY = "o";
		private static final String RESULT_KEY = "i";

		@Override
		public SearchQueryPlace getPlace (String token) {
			
			Map<String, String> entries = EscapeUtils.decodeHistoryToken(DEFAULT_KEY, token);
			if (entries.isEmpty()) return null;
			
			Search search = new Search(
					entries.get(DEFAULT_KEY),
					Search.Ordering.fromShortName(entries.get(ORDERING_KEY))
			);

			int firstResult = 0;
			try {
				String strFirstResult = entries.get(RESULT_KEY);
				if (strFirstResult != null) {
					firstResult = Integer.parseInt(strFirstResult.trim());
				}
			}
			catch (NumberFormatException e) { }
			
			return new SearchQueryPlace(search, firstResult);
		}

		@Override
		public String getToken (SearchQueryPlace searchPlace) {

			Map<String, String> entries = new HashMap<String, String>();

			entries.put(DEFAULT_KEY, searchPlace.getSearch().getQuery());
			
			if (searchPlace.getSearch().getOrdering() != Search.Ordering.DEFAULT) {
				entries.put(ORDERING_KEY, searchPlace.getSearch().getOrdering().getShortName());
			}
			
			if (searchPlace.getFirstResult() > 0) {
				entries.put(RESULT_KEY, Integer.toString(searchPlace.getFirstResult()));
			}
			
			return EscapeUtils.encodeHistoryToken(DEFAULT_KEY, entries);
		}

	}

	
}
