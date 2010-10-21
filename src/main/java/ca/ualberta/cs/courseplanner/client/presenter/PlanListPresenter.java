package ca.ualberta.cs.courseplanner.client.presenter;


import java.util.Arrays;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.ResettableEventBus;
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
	
	private final ResettableEventBus eventBus;
	
	public PlanListPresenter (PlanManager planManager, EventBus eventBus) {
		this.planManager = planManager;
		this.eventBus = new ResettableEventBus(eventBus);
	}
	
	public void start () {
		eventBus.addHandler(PlanListChangedEvent.TYPE, this);
		planManager.getPlans(this);
	}
	
	public void stop () {
		eventBus.removeHandlers();
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
