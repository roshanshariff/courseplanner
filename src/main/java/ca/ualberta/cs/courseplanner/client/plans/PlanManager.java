package ca.ualberta.cs.courseplanner.client.plans;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Label;

public class PlanManager extends Composite {
	
	interface Binder extends UiBinder<DisclosurePanel, PlanManager> { }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	DisclosurePanel panel;
	@UiField Button createButton;
	@UiField Label guestLabel;
	
	public PlanManager () {
		panel = binder.createAndBindUi(this);
		initWidget(panel);
		panel.setOpen(true);
	}
}
