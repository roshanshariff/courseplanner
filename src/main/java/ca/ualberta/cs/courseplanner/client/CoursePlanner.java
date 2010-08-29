package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.plans.PlanIntentHandler;
import ca.ualberta.cs.courseplanner.client.plans.PlanManager;
import ca.ualberta.cs.courseplanner.client.plans.PlanManagerImpl;
import ca.ualberta.cs.courseplanner.client.presenter.PlanListPresenter;
import ca.ualberta.cs.courseplanner.services.CourseDataService;
import ca.ualberta.cs.courseplanner.services.CourseDataServiceAsync;
import ca.ualberta.cs.courseplanner.services.UserDataService;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;


public class CoursePlanner implements EntryPoint {
	
	interface Binder extends UiBinder<FlowPanel, CoursePlanner> { }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField FlowPanel planList;
	
	@UiField FlowPanel searchList;
	
	@UiField FlowPanel content;
	
	private final HandlerManager eventBus = new HandlerManager(null);
	
	private final CourseDataServiceAsync courseDataService =
		(CourseDataServiceAsync) GWT.create(CourseDataService.class);
	
	private final UserDataServiceAsync userDataService =
		(UserDataServiceAsync) GWT.create(UserDataService.class);
	
	private final PlanManager planManager = new PlanManagerImpl(userDataService, eventBus);
	
	private final PlanListPresenter planListPresenter = new PlanListPresenter(planManager, eventBus);
	
	private final PlanIntentHandler planIntentHandler = new PlanIntentHandler(planManager, eventBus);
	
	public void onModuleLoad () {
		
		Document.get().getBody().getStyle().setOverflow(Style.Overflow.HIDDEN);
		RootLayoutPanel.get().add(new ScrollPanel(binder.createAndBindUi(this)));
		
		MainPresenter controller = new MainPresenter(courseDataService, eventBus);
		controller.present(content);
		
		planListPresenter.setContainer(planList);
		planListPresenter.start();
		
		planIntentHandler.start();
		
	}
	
	@UiHandler("createPlanLink")
	void handleCreatePlan (ClickEvent event) {
		eventBus.fireEvent(new CreatePlanIntent());
	}

}
