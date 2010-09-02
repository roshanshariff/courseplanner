package ca.ualberta.cs.courseplanner.client.presenter;


import java.util.Arrays;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.HasData;

import ca.ualberta.cs.courseplanner.client.events.PlanListChangedEvent;
import ca.ualberta.cs.courseplanner.client.plans.PlanManager;
import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class PlanListPresenter extends AbstractDataProvider<PlanInfo> implements PlanListChangedEvent.Handler {
	
	private PlanInfo[] plans;
	
	private final PlanManager planManager;
	
	private final HandlerManager eventBus;
	
	public PlanListPresenter (PlanManager planManager, HandlerManager eventBus) {
		this.planManager = planManager;
		this.eventBus = eventBus;
	}
	
	public void start () {
		eventBus.addHandler(PlanListChangedEvent.TYPE, this);
		planManager.getPlans(this);
	}
	
	public void stop () {
		eventBus.removeHandler(PlanListChangedEvent.TYPE, this);
	}

	@Override
	public void onPlanListChanged (PlanInfo[] plans) {
		this.plans = plans;
		update();
	}
	
	@Override
	protected void onRangeChanged (HasData<PlanInfo> arg0) {
		update();
	}

	private void update () {
		if (plans != null) {
			updateRowCount(plans.length, true);
			updateRowData(0, Arrays.asList(plans));
		}
	}

}
