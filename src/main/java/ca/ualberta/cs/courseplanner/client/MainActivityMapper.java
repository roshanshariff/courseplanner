package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.activity.DefaultActivity;
import ca.ualberta.cs.courseplanner.client.activity.DefaultPlace;
import ca.ualberta.cs.courseplanner.client.search.SearchActivity;
import ca.ualberta.cs.courseplanner.client.search.place.SearchPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;


@Singleton
public class MainActivityMapper implements ActivityMapper {
	
	private final Provider<DefaultActivity> defaultActivity;
	private final Provider<SearchActivity> searchActivity;
	
	@Inject
	public MainActivityMapper (
			Provider<DefaultActivity> defaultActivity,
			Provider<SearchActivity> searchActivity
	) {
		this.defaultActivity = defaultActivity;
		this.searchActivity = searchActivity;
	}

	@Override
	public Activity getActivity (Place place) {
		if (place instanceof DefaultPlace) return get ((DefaultPlace)place);
		if (place instanceof SearchPlace) return get ((SearchPlace)place);
		return null;
	}
	
	private Activity get (DefaultPlace place) {
		return defaultActivity.get();
	}
	
	private Activity get (SearchPlace place) {
		SearchActivity activity = searchActivity.get();
		if (activity.setNewPlace(place)) return activity;
		else return null;
	}

}
