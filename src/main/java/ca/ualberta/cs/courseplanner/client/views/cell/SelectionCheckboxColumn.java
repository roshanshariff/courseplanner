package ca.ualberta.cs.courseplanner.client.views.cell;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.SelectionModel;


public class SelectionCheckboxColumn<T> extends Column<T, Boolean> implements FieldUpdater<T, Boolean> {

	private final SelectionModel<T> selectionModel;
	

	public SelectionCheckboxColumn (SelectionModel<T> selectionModel) {
		super(new CheckboxCell(true));
		this.selectionModel = selectionModel;
		setFieldUpdater(this);
	}


	@Override
	public Boolean getValue (T object) {
		return selectionModel.isSelected(object);
	}


	@Override
	public void update (int index, T object, Boolean value) {
		selectionModel.setSelected(object, value);
	}

}
