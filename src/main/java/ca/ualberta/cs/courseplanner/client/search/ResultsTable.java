package ca.ualberta.cs.courseplanner.client.search;

import ca.ualberta.cs.courseplanner.client.views.cell.CourseHyperlinkRenderer;
import ca.ualberta.cs.courseplanner.client.views.cell.SafeHtmlRendererCell;
import ca.ualberta.cs.courseplanner.client.views.cell.SelectionCheckboxColumn;
import ca.ualberta.cs.courseplanner.model.CourseInfo;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.SelectionModel;


public class ResultsTable extends CellTable<CourseInfo> {

	private static final int PAGE_SIZE = 25;

	private final Column<CourseInfo, ?> courseHyperlinkColumn =
		new IdentityColumn<CourseInfo>(
				new SafeHtmlRendererCell<CourseInfo>(
						new CourseHyperlinkRenderer()
				)
		);

	private final Column<CourseInfo, ?> courseNameColumn = new TextColumn<CourseInfo>() {
		@Override
		public String getValue (CourseInfo course) {
			return course.getName();
		}
	};

	private final Column<CourseInfo, ?> selectionColumn;
	
	private boolean collapsed = false;

	public ResultsTable (SelectionModel<CourseInfo> selectionModel) {
		super (PAGE_SIZE, CourseInfo.KEY_PROVIDER);
		setSelectionModel(selectionModel);
		selectionColumn = new SelectionCheckboxColumn<CourseInfo>(selectionModel);
		addColumn(selectionColumn);
		addColumn(courseHyperlinkColumn);
		addColumn(courseNameColumn);
	}
	
	boolean isCollapsed () {
		return collapsed;
	}
	
	void setCollapsed (boolean collapsed) {
		if (this.collapsed != collapsed) {
			this.collapsed = collapsed;
			if (collapsed) {
				removeColumn(selectionColumn);
				removeColumn(courseNameColumn);
			}
			else {
				removeColumn(courseHyperlinkColumn);
				addColumn(selectionColumn);
				addColumn(courseHyperlinkColumn);
				addColumn(courseNameColumn);
			}
		}
	}

}
