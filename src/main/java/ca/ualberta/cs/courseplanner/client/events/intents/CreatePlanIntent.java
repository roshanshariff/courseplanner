package ca.ualberta.cs.courseplanner.client.events.intents;

import ca.ualberta.cs.courseplanner.model.PlanInfo;


public class CreatePlanIntent extends Intent<PlanInfo, CreatePlanIntent.Handler> {
	
	public static interface Handler extends Intent.Handler {
		
		void createPlan (CreatePlanIntent intent);

	}

	public static final Type<Handler> TYPE = new Type<Handler>();
	
	@Override
	protected void dispatch (Handler handler) {
		handler.createPlan (this);
	}

	@Override
	public Type<Handler> getAssociatedType () {
		return TYPE;
	}
	
	public CreatePlanIntent (Callback<PlanInfo> callback) {
		super(callback);
	}
	
	public CreatePlanIntent () {
		super();
	}
	
}
