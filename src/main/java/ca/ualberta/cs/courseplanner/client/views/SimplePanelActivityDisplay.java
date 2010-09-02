package ca.ualberta.cs.courseplanner.client.views;

import com.google.gwt.app.place.Activity;
import com.google.gwt.app.place.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;


public class SimplePanelActivityDisplay extends SimplePanel implements Activity.Display {

	@Override
	public void showActivityWidget (IsWidget widget) {
		if (widget != null) setWidget(widget.asWidget());
		else clear();
	}

}
