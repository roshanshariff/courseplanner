package ca.ualberta.cs.courseplanner.client.search;

import com.google.gwt.app.place.AbstractActivity;
import com.google.gwt.event.shared.EventBus;


public class SearchActivity extends AbstractActivity {
	
	private static final int PAGE_SIZE = 50;
	
	boolean savedSearch;
	
	String queryString;
	
	public void setSearchPlace (SearchPlace place) {
		savedSearch = false;
	}
	
	public void setSavedSearchPlace (SavedSearchPlace place) {
		
	}

	@Override
	public void start (Display display, EventBus eventBus) {
		// TODO Auto-generated method stub

	}


}
