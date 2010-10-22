package ca.ualberta.cs.courseplanner.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;


public class Main implements EntryPoint {
	
	@GinModules(MainModule.class)
	public static interface CoursePlannerInjector extends Ginjector {
		CoursePlanner getCoursePlanner ();
	}
	
	private static final CoursePlannerInjector INJECTOR = GWT.create(CoursePlannerInjector.class);

	@Override
	public void onModuleLoad () {
		Document.get().getBody().getStyle().setOverflow(Style.Overflow.HIDDEN);
		RootLayoutPanel.get().add(new ScrollPanel(INJECTOR.getCoursePlanner()));
	}

}
