package ca.ualberta.cs.courseplanner.client.plans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ca.ualberta.cs.courseplanner.client.events.PlanCreatedEvent;
import ca.ualberta.cs.courseplanner.client.events.PlanListChangedEvent;
import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;


public class PlanManagerImpl implements PlanManager {
	
	private final UserDataServiceAsync userDataService;
	
	private final HandlerManager eventBus;
	
	private List<PlanInfo> plans = new ArrayList<PlanInfo>();
	
	public PlanManagerImpl (UserDataServiceAsync userDataService, HandlerManager eventBus) {
		this.userDataService = userDataService;
		this.eventBus = eventBus;
	}

	@Override
	public void getPlans (final PlanListChangedEvent.Handler callback) {
		userDataService.getPlans(new AsyncCallback<PlanInfo[]>() {
			
			@Override
			public void onSuccess (final PlanInfo[] plans) {
				Arrays.sort(plans);
				callback.onPlanListChanged(plans);
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute () {
						planListChanged (plans);
					}
				});
			}
			
			@Override
			public void onFailure (Throwable arg0) { }

		});
	}

	@Override
	public void createPlan (String planName, final PlanCreatedEvent.Handler callback) {
		userDataService.createPlan(planName, new AsyncCallback<PlanInfo>() {

			@Override
			public void onSuccess (final PlanInfo plan) {
				callback.onPlanCreated(plan);
				DeferredCommand.addCommand(new Command() {
					@Override
					public void execute () {
						planCreated (plan);
					}
				});
			}
			
			@Override
			public void onFailure (Throwable arg0) { }

		});
	}
	
	protected void planListChanged (PlanInfo[] plansArray) {
		List<PlanInfo> newPlans = Arrays.asList(plansArray);
		if (!plans.equals(newPlans)) {
			plans.clear();
			plans.addAll(newPlans);
			firePlanListChangedEvent();
		}
	}
	
	protected void planCreated (PlanInfo newPlan) {
		firePlanCreatedEvent(newPlan);
		PlanInfo oldPlan = getPlan(newPlan.getId());
		if (oldPlan == null || !oldPlan.equals(newPlan)) {
			if (oldPlan != null) plans.remove(oldPlan);
			plans.add(newPlan);
			Collections.sort(plans);
			firePlanListChangedEvent();
		}
	}
	
	protected PlanInfo getPlan (long id) {
		for (PlanInfo plan : plans) {
			if (plan.getId() == id) {
				return plan;
			}
		}
		return null;
	}

	protected void firePlanListChangedEvent () {
		eventBus.fireEvent(new PlanListChangedEvent(plans.toArray(new PlanInfo[plans.size()])));
	}

	protected void firePlanCreatedEvent (PlanInfo plan) {
		eventBus.fireEvent(new PlanCreatedEvent(plan));
	}
	
}
