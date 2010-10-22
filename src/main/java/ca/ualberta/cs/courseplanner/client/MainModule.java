package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.activity.PlaceAwareActivityMapper;
import ca.ualberta.cs.courseplanner.client.search.SearchCoursesActivity;
import ca.ualberta.cs.courseplanner.client.search.SearchCoursesView;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;


public class MainModule extends AbstractGinModule {

	@Override
	protected void configure () {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(SearchCoursesActivity.View.class).to(SearchCoursesView.class);
		bind(ActivityMapper.class).to(MainActivityMapper.class).in(Singleton.class);
		bind(PlaceHistoryMapper.class).to(MainPlaceHistoryMapper.class).in(Singleton.class);
	}
	
	@Provides @Singleton @Inject
	ActivityManager createActivityManager (EventBus eventBus, ActivityMapper activityMapper) {
		return new ActivityManager(new PlaceAwareActivityMapper(activityMapper), eventBus);
	}
	
	@Provides @Singleton @Inject
	PlaceController createPlaceController (EventBus eventBus) {
		return new PlaceController(eventBus);
	}
	
	@Provides @Singleton @Inject
	PlaceHistoryHandler createPlaceHistoryHandler (PlaceHistoryMapper mapper) {
		return new PlaceHistoryHandler(mapper);
	}

}
