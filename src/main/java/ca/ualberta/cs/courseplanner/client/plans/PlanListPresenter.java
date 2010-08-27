package ca.ualberta.cs.courseplanner.client.plans;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import ca.ualberta.cs.courseplanner.client.presenter.Presenter;
import ca.ualberta.cs.courseplanner.model.PlanInfo;

public interface PlanListPresenter extends Presenter {
	
	public static interface View {
		
		public Widget getWidget ();
		
		public void setPresenter (PlanListPresenter presenter);
		
		public void setPlanList (List<PlanInfo> plans);
		
	}
	
	public void createPlan ();

}
