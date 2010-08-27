package ca.ualberta.cs.courseplanner.client.plans;

import java.util.List;

import ca.ualberta.cs.courseplanner.model.PlanInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class PlanList extends Composite {
	
	interface Binder extends UiBinder<Widget, PlanList> { }
	
	interface Style extends CssResource { String item(); }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField ComplexPanel panel;

	@UiField Style style;
	
	private List<PlanInfo> plans;
	
	public PlanList () {
		initWidget(binder.createAndBindUi(this));
	}
	
	public void setPlans (List<PlanInfo> plans) {

		this.plans = plans;
		panel.clear();

		for (PlanInfo plan : plans) {
			Hyperlink link = new Hyperlink(plan.getName(), false, "plan:"+plan.getId());
			link.setStylePrimaryName(style.item());
			panel.add(link);
		}
	}

}
