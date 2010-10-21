package ca.ualberta.cs.courseplanner.client.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


public class WidgetCollection implements HasWidgets {
	
	private final Collection<Widget> widgets = new ArrayList<Widget>();

	@Override
	public void add (Widget widget) {
		widgets.add(widget);
	}

	@Override
	public void clear () {
		widgets.clear();
	}

	@Override
	public Iterator<Widget> iterator () {
		return widgets.iterator();
	}

	@Override
	public boolean remove (Widget widget) {
		return widgets.remove(widget);
	}

}
