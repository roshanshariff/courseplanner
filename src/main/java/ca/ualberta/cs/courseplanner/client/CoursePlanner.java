package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.activity.DefaultPlace;
import ca.ualberta.cs.courseplanner.client.activity.PlaceAwareActivityMapper;
import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.intents.SearchCoursesIntent;
import ca.ualberta.cs.courseplanner.client.plans.PlanIntentHandler;
import ca.ualberta.cs.courseplanner.client.plans.PlanListProvider;
import ca.ualberta.cs.courseplanner.client.search.SearchCoursesActivity;
import ca.ualberta.cs.courseplanner.client.views.cell.PlanHyperlinkRenderer;
import ca.ualberta.cs.courseplanner.client.views.cell.SafeHtmlRendererCell;
import ca.ualberta.cs.courseplanner.model.PlanInfo;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.activity.shared.CachingActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class CoursePlanner extends Composite {

	interface Binder extends UiBinder<FlowPanel, CoursePlanner> { }

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField(provided=true) CellList<PlanInfo> planList =
		new CellList<PlanInfo>(
				new SafeHtmlRendererCell<PlanInfo>(new PlanHyperlinkRenderer()),
				PlanInfo.KEY_PROVIDER
		);
	@UiField FlowPanel searchList;
	@UiField AcceptsOneWidget searchCoursesDisplay;
	@UiField AcceptsOneWidget mainDisplay;

	private final EventBus eventBus;

	@Inject
	public CoursePlanner (
			EventBus eventBus,
			ActivityManager activityManager,
			PlaceHistoryHandler placeHistoryHandler,
			PlaceController placeController,
			PlanIntentHandler planIntentHandler,
			PlanListProvider planListProvider,
			SearchCoursesActivity searchCoursesActivity
	) {

		initWidget(BINDER.createAndBindUi(this));

		this.eventBus = eventBus;
	
		activityManager.setDisplay(mainDisplay);
		placeHistoryHandler.register(placeController, eventBus, new DefaultPlace());
		placeHistoryHandler.handleCurrentHistory();

		planIntentHandler.addHandler(eventBus);

		planList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		planListProvider.addDataDisplay(planList);

		searchCoursesActivity.start(searchCoursesDisplay, eventBus);
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
