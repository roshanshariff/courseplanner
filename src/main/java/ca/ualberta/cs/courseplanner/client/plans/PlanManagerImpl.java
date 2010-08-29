package ca.ualberta.cs.courseplanner.client.plans;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ca.ualberta.cs.courseplanner.client.events.PlanListChangedEvent.Handler;
import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;


public class PlanManagerImpl implements PlanManager {
	
	private final UserDataServiceAsync userDataService;
	
	public PlanManagerImpl (UserDataServiceAsync userDataService) {
		this.userDataService = userDataService;
	}

	@Override
	public void getPlans (final Handler callback) {
		userDataService.getPlans(new AsyncCallback<PlanInfo[]>() {
			
			@Override
			public void onSuccess (PlanInfo[] plans) {
				callback.onPlanListChanged(plans);
			}
			
			@Override
			public void onFailure (Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

		});
	}

}
