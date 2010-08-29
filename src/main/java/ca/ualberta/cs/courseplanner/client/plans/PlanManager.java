package ca.ualberta.cs.courseplanner.client.plans;

import ca.ualberta.cs.courseplanner.client.events.PlanListChangedEvent;


public interface PlanManager {
	
	void getPlans (PlanListChangedEvent.Handler callback);
	
}
