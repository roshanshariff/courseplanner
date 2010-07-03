package ca.ualberta.cs.courseplanner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;


public class CoursePlanner implements EntryPoint {

	public void onModuleLoad () {
		CourseDataServiceAsync courseDataService = (CourseDataServiceAsync) GWT.create(CourseDataService.class);
		HandlerManager eventBus = new HandlerManager(null);
		MainPresenter controller = new MainPresenter(courseDataService, eventBus);
		controller.present(RootPanel.get());
	}

}
