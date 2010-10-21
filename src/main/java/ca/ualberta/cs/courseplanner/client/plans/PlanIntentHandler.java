package ca.ualberta.cs.courseplanner.client.plans;

import com.google.gwt.event.shared.EventBus;

import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.presenter.PlanCreatePresenter;
import ca.ualberta.cs.courseplanner.client.views.PromptDialogView;

public class PlanIntentHandler implements CreatePlanIntent.Handler {
	
	private final PlanManager planManager;
	
	private final EventBus eventBus;
	
	public PlanIntentHandler (PlanManager planManager, EventBus eventBus) {
		this.planManager = planManager;
		this.eventBus = eventBus;
	}
	
	public void start () {
		eventBus.addHandler(CreatePlanIntent.TYPE, this);
	}
	
	public void stop () {
		eventBus.addHandler(CreatePlanIntent.TYPE, this);
	}

	@Override
	public void createPlan (CreatePlanIntent intent) {
		PromptDialogView view = new PromptDialogView();
		PlanCreatePresenter presenter = new PlanCreatePresenter(view, planManager, eventBus);
		presenter.setIntent(intent);
		presenter.start();
	}

}
