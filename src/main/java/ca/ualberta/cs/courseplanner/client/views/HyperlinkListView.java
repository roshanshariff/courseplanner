package ca.ualberta.cs.courseplanner.client.views;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class HyperlinkListView<T> extends Composite {

	public static interface Delegate<T> {
		String getText (T item);
		String getTarget (T item);
		boolean asHTML ();
		Widget getEmptyWidget ();
	}

	private final Delegate<T> delegate;

	private final Panel panel;

	public HyperlinkListView (Delegate<T> delegate) {
		this.delegate = delegate;
		this.panel = new FlowPanel();
		initWidget(panel);
	}

	public void setData (List<T> data) {
		panel.clear();
		if (!data.isEmpty()) {
			for (T item : data) {
				panel.add(new Hyperlink(delegate.getText(item), delegate.asHTML(),
						delegate.getTarget(item)));
			}
		}
		else {
			Widget emptyWidget = delegate.getEmptyWidget();
			if (emptyWidget != null) {
				panel.add(emptyWidget);
			}
		}
	}

}
