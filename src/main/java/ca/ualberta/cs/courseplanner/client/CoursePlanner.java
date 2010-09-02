package ca.ualberta.cs.courseplanner.client;

import java.util.Arrays;
import java.util.Collections;

import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.intents.SearchCoursesIntent;
import ca.ualberta.cs.courseplanner.client.plans.PlanIntentHandler;
import ca.ualberta.cs.courseplanner.client.plans.PlanManager;
import ca.ualberta.cs.courseplanner.client.plans.PlanManagerImpl;
import ca.ualberta.cs.courseplanner.client.presenter.PlanListPresenter;
import ca.ualberta.cs.courseplanner.client.presenter.SearchCoursesPresenter;
import ca.ualberta.cs.courseplanner.client.views.PlanList;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.services.CourseDataService;
import ca.ualberta.cs.courseplanner.services.CourseDataServiceAsync;
import ca.ualberta.cs.courseplanner.services.UserDataService;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;

import com.google.gwt.app.place.Activity;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;


public class CoursePlanner implements EntryPoint {
	
	interface Binder extends UiBinder<FlowPanel, CoursePlanner> { }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField PlanList planList;
	
	@UiField FlowPanel searchList;
	
	@UiField SearchCoursesPresenter.View searchCoursesView;
	
	@UiField Activity.Display content;
	
	@UiField(provided=true) CellList<CourseInfo> table = new CellList<CourseInfo>(new AbstractCell<CourseInfo>(Collections.<String>emptySet()) {

		@Override
		public void render (CourseInfo course, Object key, StringBuilder arg2) {
			arg2.append("<a href=\"#c:")
				.append(course.getId())
				.append("\"><b>")
				.append(course.getSubjectId())
				.append(" ")
				.append(course.getNumber())
				.append("</b> ")
				.append(course.getName())
				.append("</a>");
		}
		
	});
	
	private final HandlerManager eventBus = new HandlerManager(null);
	
	private final CourseDataServiceAsync courseDataService =
		(CourseDataServiceAsync) GWT.create(CourseDataService.class);
	
	private final UserDataServiceAsync userDataService =
		(UserDataServiceAsync) GWT.create(UserDataService.class);
	
	private final PlanManager planManager = new PlanManagerImpl(userDataService, eventBus);
	
	private final PlanListPresenter planListPresenter = new PlanListPresenter(planManager, eventBus);
	
	private final PlanIntentHandler planIntentHandler = new PlanIntentHandler(planManager, eventBus);
	
	private final SearchCoursesPresenter searchCoursesPresenter = new SearchCoursesPresenter (eventBus);
	
	public void onModuleLoad () {
		
		Document.get().getBody().getStyle().setOverflow(Style.Overflow.HIDDEN);
		RootLayoutPanel.get().add(new ScrollPanel(binder.createAndBindUi(this)));
		
		planListPresenter.start();
		planListPresenter.addDataDisplay(planList);
		
		planIntentHandler.start();
		
		searchCoursesPresenter.setView(searchCoursesView);
		searchCoursesPresenter.start();
		
		table.setRowCount(2);
		
		table.setRowData(0, Arrays.asList(
				new CourseInfo(1, "ACCTG", "300", "Introduction to Accounting"),
				new CourseInfo(1, "CMPUT", "114", "Introduction to Computing Science")
		));
		
	}
	
	@UiHandler("createPlanLink")
	void handleCreatePlan (ClickEvent event) {
		eventBus.fireEvent(new CreatePlanIntent());
	}
	
	@UiHandler("searchCoursesLink")
	void handleSearchCourses (ClickEvent event) {
		eventBus.fireEvent(new SearchCoursesIntent());
	}

}
