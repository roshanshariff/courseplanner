package ca.ualberta.cs.courseplanner.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ualberta.cs.courseplanner.client.views.HyperlinkListView;
import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;
import ca.ualberta.cs.courseplanner.services.CourseDataService;
import ca.ualberta.cs.courseplanner.services.CourseDataServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class CoursePlanner implements EntryPoint {
	
	interface Binder extends UiBinder<FlowPanel, CoursePlanner> { }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField(provided=true) HyperlinkListView<PlanInfo> planList =
		new HyperlinkListView<PlanInfo>(new HyperlinkListView.Delegate<PlanInfo>() {

			@Override
			public String getText(PlanInfo item) {
				return item.getName();
			}

			@Override
			public String getTarget(PlanInfo item) {
				return "plan:"+item.getId();
			}

			@Override
			public boolean asHTML() {
				return false;
			}

			@Override
			public Widget getEmptyWidget() {
				return null;
			}

		});
	
	@UiField(provided=true) HyperlinkListView<SavedSearchInfo> searchList =
		new HyperlinkListView<SavedSearchInfo>(null);
	
	@UiField FlowPanel content;

	public void onModuleLoad () {
		
		FlowPanel main = binder.createAndBindUi(this);
		RootPanel root = RootPanel.get();
		root.add(main);
		
		CourseDataServiceAsync courseDataService = (CourseDataServiceAsync) GWT.create(CourseDataService.class);
		HandlerManager eventBus = new HandlerManager(null);
		MainPresenter controller = new MainPresenter(courseDataService, eventBus);
		controller.present(content);
		
		List<PlanInfo> plans = new ArrayList<PlanInfo>();
		plans.add(new PlanInfo(1, "My First Plan"));
		plans.add(new PlanInfo(2, "Another Plan"));
		plans.add(new PlanInfo(3, "Best Plan Ever"));
		plans.add(new PlanInfo(4, "Worst Plan Ever"));
		Collections.sort(plans);
		planList.setData(plans);
	}

}
