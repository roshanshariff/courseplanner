package ca.ualberta.cs.courseplanner.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.plans.PlanListProvider;
import ca.ualberta.cs.courseplanner.client.views.PromptDialogView;
import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class PlanCreatePresenter implements PromptDialogView.Presenter {
	
	private static final String CAPTION = "Create Plan";
	private static final String PROMPT = "Please enter a new plan name:";
	private static final String EMPTY_PROMPT = "No plan name specified. Please try another name:";
	private static final String DUPLICATE_PROMPT = "This plan name already exists. Please try another name:";
	private static final String SUBMIT_TEXT = "Create";
	
	private final PromptDialogView view;
	
	private final PlanListProvider planListProvider;
	
	private CreatePlanIntent intent;
	
	@Inject
	public PlanCreatePresenter (PromptDialogView view, PlanListProvider planListProvider) {
		this.view = view;
		this.planListProvider = planListProvider;
	}
	
	public void setIntent (CreatePlanIntent intent) {
		this.intent = intent;
		view.setPresenter(this);
		view.setCaption(CAPTION);
		view.setText(PROMPT);
		view.getSubmitButton().setText(SUBMIT_TEXT);
		view.show();
	}

	public void stop () {
		view.hide();
		view.setPresenter(null);
	}

	@Override
	public void onSubmit () {
		final String planName = view.getValue();
		if (planName.isEmpty()) {
			view.setText(EMPTY_PROMPT);
		}
		else {
			view.getSubmitButton().setEnabled(false);
			planListProvider.getPlanByName(planName, new AsyncCallback<PlanInfo>() {
				
				@Override
				public void onSuccess (PlanInfo result) {
					if (result == null) {
						planListProvider.createPlan(planName, new AsyncCallback<PlanInfo>() {

							@Override
							public void onSuccess (PlanInfo newPlan) {
								intent.completed(newPlan);
								stop();
							}
							
							@Override
							public void onFailure (Throwable caught) {
								onCancel();
							}

						});
					}
					else {
						view.setText(DUPLICATE_PROMPT);
					}
					view.getSubmitButton().setEnabled(true);
				}
				
				@Override
				public void onFailure (Throwable caught) {
					onCancel();
				}
				
			});
		}
	}

	@Override
	public void onCancel () {
		intent.cancelled();
		stop();
	}
	
}
