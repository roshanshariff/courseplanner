package ca.ualberta.cs.courseplanner.client.plans;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.presenter.PlanCreatePresenter;

@Singleton
public class PlanIntentHandler implements CreatePlanIntent.Handler {
	
	private final Provider<PlanCreatePresenter> planCreateActivity;
	
	@Inject
	public PlanIntentHandler (Provider<PlanCreatePresenter> planCreateActivity) {
		this.planCreateActivity = planCreateActivity;
	}
	
	public HandlerRegistration addHandler (EventBus eventBus) {
		return eventBus.addHandler(CreatePlanIntent.TYPE, this);
	}
	
	@Override
	public void createPlan (CreatePlanIntent intent) {
		planCreateActivity.get().setIntent(intent);
	}

}
