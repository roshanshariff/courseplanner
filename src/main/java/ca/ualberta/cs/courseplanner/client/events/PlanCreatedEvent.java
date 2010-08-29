package ca.ualberta.cs.courseplanner.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class PlanCreatedEvent extends GwtEvent<PlanCreatedEvent.Handler> {
	
	public static interface Handler extends EventHandler {
		
		void onPlanCreated (PlanInfo plan);
		
	}
	
	public static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();
	
	private final PlanInfo plan;
	
	public PlanCreatedEvent (PlanInfo plan) {
		this.plan = plan;
	}

	@Override
	protected void dispatch (Handler handler) {
		handler.onPlanCreated(plan);
	}

	@Override
	public Type<Handler> getAssociatedType () {
		return TYPE;
	}

}
