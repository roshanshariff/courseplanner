package ca.ualberta.cs.courseplanner.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;

import ca.ualberta.cs.courseplanner.client.intents.SearchCoursesIntent;

public class SearchCoursesPresenter implements SearchCoursesIntent.Handler {
	
	public static interface View {
		
		void setPresenter (SearchCoursesPresenter presenter);
		
		void setFocus (boolean focus);
		
		void setAdvancedSearch (boolean advancedSearch);

	}
	
	private final EventBus eventBus;
	private HandlerRegistration intentHandlerRegistration; 
	
	private View view;
	
	public SearchCoursesPresenter (EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public void setView (View view) {
		this.view = view;
	}
	
	public void start () {
		intentHandlerRegistration = eventBus.addHandler(SearchCoursesIntent.TYPE, this);
		view.setPresenter(this);
		view.setAdvancedSearch(false);
	}
	
	public void stop () {
		if (intentHandlerRegistration != null) intentHandlerRegistration.removeHandler();
		if (view != null) view.setPresenter(null);
	}
	
	public void setAdvancedSearch (boolean advancedSearch) {
		view.setAdvancedSearch(advancedSearch);
	}

	@Override
	public void searchCourses (SearchCoursesIntent intent) {
		view.setAdvancedSearch(true);
		view.setFocus(true);
	}

}
