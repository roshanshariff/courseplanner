package ca.ualberta.cs.courseplanner.client.events;

import ca.ualberta.cs.courseplanner.model.PlanInfo;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public class PlanListChangedEvent extends GwtEvent<PlanListChangedEvent.Handler> {
	
	public static interface Handler extends EventHandler {
		
		void onPlanListChanged (PlanInfo[] plans);
		
	}
	
	public static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType () {
		return TYPE;
	}
	
	private final PlanInfo[] plans;
	
	public PlanListChangedEvent (PlanInfo[] plans) {
		this.plans = plans;
	}

	@Override
	protected void dispatch (Handler handler) {
		handler.onPlanListChanged(plans);
	}

}
