package ca.ualberta.cs.courseplanner.client.plans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ca.ualberta.cs.courseplanner.client.events.PlanCreatedEvent;
import ca.ualberta.cs.courseplanner.client.events.PlanDeletedEvent;
import ca.ualberta.cs.courseplanner.model.PlanDetails;
import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class PlanListProvider extends AsyncDataProvider<PlanInfo> {

	private final UserDataServiceAsync userDataService;

	private final EventBus eventBus;

	private List<PlanInfo> plans = new ArrayList<PlanInfo>();

	@Inject
	public PlanListProvider (UserDataServiceAsync userDataService, EventBus eventBus) {
		this.userDataService = userDataService;
		this.eventBus = eventBus;
	}

	@Override
	protected void onRangeChanged (final HasData<PlanInfo> display) {
		getPlans(new AsyncCallback<List<PlanInfo>>() {
			@Override
			public void onSuccess (List<PlanInfo> result) {
				updateRowData(display, 0, plans);
			}
			@Override
			public void onFailure (Throwable caught) { }
		});
	}

	public void getPlanById (final long id, final AsyncCallback<PlanInfo> callback) {
		getPlans (new AsyncCallback<List<PlanInfo>>() {

			@Override
			public void onSuccess (List<PlanInfo> plans) {
				for (PlanInfo plan : plans) {
					if (plan.getId() == id) {
						callback.onSuccess(plan);
						return;
					}
				}
				callback.onSuccess(null);
			}

			@Override
			public void onFailure (Throwable caught) {
				callback.onFailure(caught);
			}

		});
	}

	public void getPlanByName (final String name, final AsyncCallback<PlanInfo> callback) {
		getPlans (new AsyncCallback<List<PlanInfo>>() {

			@Override
			public void onSuccess (List<PlanInfo> plans) {
				for (PlanInfo plan : plans) {
					if (plan.getName().equals(name)) {
						callback.onSuccess(plan);
						return;
					}
				}
				callback.onSuccess(null);
			}

			@Override
			public void onFailure (Throwable caught) {
				callback.onFailure(caught);
			}

		});
	}

	public void getPlans (final AsyncCallback<List<PlanInfo>> callback) {
		if (isDataValid()) {
			if (callback != null) {
				callback.onSuccess(Collections.unmodifiableList(plans));
			}
		}
		else {
			userDataService.getPlans(new AsyncCallback<PlanInfo[]>() {

				@Override
				public void onSuccess (final PlanInfo[] planArray) {
					Arrays.sort(planArray);
					List<PlanInfo> newPlans = Arrays.asList(planArray);
					if (!plans.equals(newPlans)) {
						plans.clear();
						plans.addAll(newPlans);
						updateRowCount(plans.size(), true);
						updateRowData(0, plans);
					}
					if (callback != null) {
						callback.onSuccess(plans);
					}
				}

				@Override
				public void onFailure (Throwable caught) {
					if (callback != null) callback.onFailure(caught);
				}

			});
		}
	}

	public void createPlan (String planName, final AsyncCallback<PlanInfo> callback) {
		userDataService.createPlan(planName, new AsyncCallback<PlanDetails>() {

			@Override
			public void onSuccess (PlanDetails newPlan) {
				plans.remove(newPlan);
				plans.add(newPlan);
				Collections.sort(plans);
				updateRowCount(plans.size(), true);
				updateRowData(0, plans);
				if (callback != null) callback.onSuccess(newPlan);
				eventBus.fireEvent(new PlanCreatedEvent(newPlan));
			}

			@Override
			public void onFailure (Throwable caught) {
				if (callback != null) callback.onFailure(caught);
			}

		});
	}
	
	public void deletePlan (long id, final AsyncCallback<PlanInfo> callback) {
		userDataService.deletePlan(id, new AsyncCallback<PlanInfo>() {
			
			@Override
			public void onSuccess (PlanInfo deletedPlan) {
				plans.remove(deletedPlan);
				Collections.sort(plans);
				updateRowCount(plans.size(), true);
				updateRowData(0, plans);
				if (callback != null) callback.onSuccess(deletedPlan);
				eventBus.fireEvent(new PlanDeletedEvent(deletedPlan));
			}
			
			@Override
			public void onFailure (Throwable caught) {
				if (callback != null) callback.onFailure(caught);
			}
			
		});
	}

	private boolean isDataValid () {
		return false;
	}

}
