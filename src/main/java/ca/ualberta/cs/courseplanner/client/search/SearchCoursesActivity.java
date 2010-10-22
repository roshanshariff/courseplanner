package ca.ualberta.cs.courseplanner.client.search;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

import ca.ualberta.cs.courseplanner.client.intents.SearchCoursesIntent;
import ca.ualberta.cs.courseplanner.client.search.place.SearchQueryPlace;
import ca.ualberta.cs.courseplanner.model.Search;

public class SearchCoursesActivity extends AbstractActivity implements SearchCoursesIntent.Handler {

	public static interface View extends IsWidget {

		void setPresenter (SearchCoursesActivity presenter);

		void setFocus (boolean focus);

		void setAdvancedSearch (boolean advancedSearch);
		
		String getValue ();
		
		void setValue (String value);
		
		void setValue (String value, boolean fireEvents);

	}

	private final View view;
	
	private final PlaceController placeController;

	@Inject
	public SearchCoursesActivity (View view, PlaceController placeController) {
		this.view = view;
		this.placeController = placeController;
	}

	@Override
	public void start (AcceptsOneWidget display, EventBus eventBus) {
		view.setPresenter(this);
		view.setAdvancedSearch(false);
		eventBus.addHandler(SearchCoursesIntent.TYPE, this);
		display.setWidget(view.asWidget());
	}

	@Override
	public void onStop () {
		view.setPresenter(null);
	}

	public void setAdvancedSearch (boolean advancedSearch) {
		view.setAdvancedSearch(advancedSearch);
	}

	@Override
	public void searchCourses (SearchCoursesIntent intent) {
		setAdvancedSearch(true);
		view.setFocus(true);
	}
	
	public void onSearch () {
		placeController.goTo(new SearchQueryPlace (new Search (view.getValue(), Search.Ordering.DEFAULT), 0));
	}
	
}
