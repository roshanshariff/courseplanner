package ca.ualberta.cs.courseplanner.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class PlanDeletedEvent extends GwtEvent<PlanDeletedEvent.Handler> {
	
	public static interface Handler extends EventHandler {
		
		void onPlanDeleted (PlanInfo plan);
		
	}
	
	public static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();
	
	private final PlanInfo plan;
	
	public PlanDeletedEvent (PlanInfo plan) {
		this.plan = plan;
	}

	@Override
	protected void dispatch (Handler handler) {
		handler.onPlanDeleted(plan);
	}

	@Override
	public Type<Handler> getAssociatedType () {
		return TYPE;
	}

}
