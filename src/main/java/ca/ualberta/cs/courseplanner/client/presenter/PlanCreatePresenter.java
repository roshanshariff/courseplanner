package ca.ualberta.cs.courseplanner.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.ResettableEventBus;

import ca.ualberta.cs.courseplanner.client.events.PlanCreatedEvent;
import ca.ualberta.cs.courseplanner.client.events.PlanListChangedEvent;
import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.plans.PlanManager;
import ca.ualberta.cs.courseplanner.client.views.PromptDialogView;
import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class PlanCreatePresenter implements PromptDialogView.Presenter, PlanListChangedEvent.Handler, PlanCreatedEvent.Handler {
	
	private static final String CAPTION = "Create Plan";
	
	private static final String PROMPT = "Please enter a new plan name:";
	
	private static final String EMPTY_PROMPT = "No plan name specified. Please try another name:";
	
	private static final String DUPLICATE_PROMPT = "This plan name already exists. Please try another name:";
	
	private static final String SUBMIT_TEXT = "Create";
	
	private final PromptDialogView view;
	
	private final PlanManager planManager;
	
	private final ResettableEventBus eventBus;
	
	private CreatePlanIntent intent;
	
	private PlanInfo[] plans;
	
	public PlanCreatePresenter (PromptDialogView view, PlanManager planManager, EventBus eventBus) {
		this.view = view;
		this.planManager = planManager;
		this.eventBus = new ResettableEventBus(eventBus);
	}
	
	public void setIntent (CreatePlanIntent intent) {
		this.intent = intent;
	}
	
	public void start () {
		eventBus.addHandler(PlanListChangedEvent.TYPE, this);
		planManager.getPlans(this);
		view.setPresenter(this);
		view.setCaption(CAPTION);
		view.setPrompt(PROMPT);
		view.setSubmitText(SUBMIT_TEXT);
		view.show();
	}
	
	public void stop () {
		view.hide();
		view.setPresenter(null);
		eventBus.removeHandlers();
	}

	@Override
	public void onPlanListChanged (PlanInfo[] plans) {
		this.plans = plans;
	}
	
	@Override
	public void onSubmit () {
		String planName = view.getValue();
		if (planName.isEmpty()) {
			view.setPrompt(EMPTY_PROMPT);
		}
		else if (isDuplicate(planName)) {
			view.setPrompt(DUPLICATE_PROMPT);
		}
		else {
			planManager.createPlan(planName, this);
			stop();
		}
	}

	@Override
	public void onCancel () {
		intent.cancelled();
		stop();
	}
	
	@Override
	public void onPlanCreated (PlanInfo plan) {
		intent.completed(plan);
	}

	private boolean isDuplicate (String planName) {
		if (plans != null) {
			for (PlanInfo plan : plans) {
				if (plan.getName().equals(planName)) {
					return true;
				}
			}
		}
		return false;
	}

}
