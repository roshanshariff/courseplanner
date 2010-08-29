package ca.ualberta.cs.courseplanner.client.presenter;


import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;

import ca.ualberta.cs.courseplanner.client.events.PlanListChangedEvent;
import ca.ualberta.cs.courseplanner.client.plans.PlanManager;
import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class PlanListPresenter implements PlanListChangedEvent.Handler {
	
	private PlanInfo[] plans;
	
	private final PlanManager planManager;
	
	private final HandlerManager eventBus;
	
	private HasWidgets container;
	
	public PlanListPresenter (PlanManager planManager, HandlerManager eventBus) {
		this.planManager = planManager;
		this.eventBus = eventBus;
	}
	
	public void setContainer (HasWidgets container) {
		this.container = container;
	}
	
	public void start () {
		eventBus.addHandler(PlanListChangedEvent.TYPE, this);
		planManager.getPlans(this);
	}
	
	public void stop () {
		eventBus.removeHandler(PlanListChangedEvent.TYPE, this);
		container.clear();
	}

	@Override
	public void onPlanListChanged (PlanInfo[] plans) {
		this.plans = plans;
		update();
	}
	
	private void update () {
		container.clear();
		for (PlanInfo plan : plans) {
			container.add(new Hyperlink(plan.getName(), false, "plan:"+plan.getId()));
		}		
	}

}
