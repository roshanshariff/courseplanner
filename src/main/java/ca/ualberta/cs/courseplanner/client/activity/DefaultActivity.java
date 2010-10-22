package ca.ualberta.cs.courseplanner.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;


public class DefaultActivity extends AbstractActivity {
	
	interface Binder extends UiBinder<IsWidget, DefaultActivity> { }
	
	private static final Binder BINDER = GWT.create(Binder.class);

	@Override
	public void start (AcceptsOneWidget arg0, EventBus arg1) {
		arg0.setWidget(BINDER.createAndBindUi(this));
	}

}
