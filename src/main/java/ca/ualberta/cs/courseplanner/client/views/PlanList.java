package ca.ualberta.cs.courseplanner.client.views;

import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.util.EscapeUtils;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;


public class PlanList extends CellList<PlanInfo> {
	
	public static final Cell<PlanInfo> CELL = new AbstractCell<PlanInfo>() {

		@Override
		public void render (PlanInfo plan, Object key, SafeHtmlBuilder html) {

		}

	};
	
	public PlanList () {
		super(CELL);
		setVisibleRange(0, Integer.MAX_VALUE);
	}

}
