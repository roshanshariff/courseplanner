package ca.ualberta.cs.courseplanner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;


public class CoursePlanner extends UIObject implements EntryPoint {
	
	interface Binder extends UiBinder<DockLayoutPanel, CoursePlanner> { }
	
	private static final Binder binder = GWT.create(Binder.class);

	public void onModuleLoad () {
		
		DockLayoutPanel main = binder.createAndBindUi(this);
		RootLayoutPanel root = RootLayoutPanel.get();
		//root.add(main);
		
		DataServiceAsync courseDataService = (DataServiceAsync) GWT.create(DataService.class);
		HandlerManager eventBus = new HandlerManager(null);
		MainPresenter controller = new MainPresenter(courseDataService, eventBus);
		controller.present(root);
	}

}
