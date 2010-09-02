package ca.ualberta.cs.courseplanner.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import ca.ualberta.cs.courseplanner.client.presenter.SearchCoursesPresenter;

public class SearchCoursesView extends Composite implements SearchCoursesPresenter.View {
	
	interface Binder extends UiBinder<Widget, SearchCoursesView> { }
	
	private static final Binder BINDER = GWT.create(Binder.class);
	
	private SearchCoursesPresenter presenter;
	
	@UiField TextBox searchField;
	
	@UiField TextBox advancedSearchField;
	
	@UiField ToggleButton optionsButton;
	
	@UiField DisclosurePanel optionsPanel;
	
	public SearchCoursesView () {
		initWidget(BINDER.createAndBindUi(this));
	}
	
	public void setPresenter (SearchCoursesPresenter presenter) {
		this.presenter = presenter;
	}
	
	public void setFocus (boolean focus) {
		if (!optionsPanel.isOpen()) searchField.setFocus(focus);
		else advancedSearchField.setFocus(focus);
	}
	
	@UiHandler("optionsButton")
	void onOptionsButtonClick (ClickEvent event) {
		if (presenter != null) {
			presenter.setAdvancedSearch(optionsButton.isDown());
		}
	}
	
	public void setAdvancedSearch (boolean advancedSearch) {
		if (optionsButton.isDown() != advancedSearch) optionsButton.setDown(advancedSearch);
		if (optionsPanel.isOpen() != advancedSearch) optionsPanel.setOpen(advancedSearch);
		searchField.setVisible(!advancedSearch);
	}

}
