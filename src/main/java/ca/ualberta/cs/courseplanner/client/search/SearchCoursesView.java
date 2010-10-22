package ca.ualberta.cs.courseplanner.client.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;


public class SearchCoursesView extends Composite implements SearchCoursesActivity.View {
	
	interface Binder extends UiBinder<Widget, SearchCoursesView> { }
	
	private static final Binder BINDER = GWT.create(Binder.class);
	
	private SearchCoursesActivity presenter;
	
	@UiField TextBox searchField;
	@UiField TextBox advancedSearchField;
	@UiField PushButton searchButton;
	
	@UiField ToggleButton optionsButton;
	@UiField DisclosurePanel optionsPanel;
	
	public SearchCoursesView () {
		initWidget(BINDER.createAndBindUi(this));
	}
	
	public void setPresenter (SearchCoursesActivity presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("optionsButton")
	void onOptionsButtonClick (ClickEvent event) {
		if (presenter != null) {
			presenter.setAdvancedSearch(optionsButton.isDown());
		}
	}
	
	@UiHandler("searchButton")
	void onSearchButtonClick (ClickEvent event) {
		if (presenter != null) {
			presenter.onSearch();
		}
	}
	
	@UiHandler("searchField")
	void onSearchFieldKey (KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			onSearchButtonClick(null);
		}
	}
	
	@UiHandler("advancedSearchField")
	void onAdvancedSearchFieldKey (KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			onSearchButtonClick(null);
		}
	}
	
	public void setAdvancedSearch (boolean advancedSearch) {
		getHiddenTextField().setValue(getVisibleSearchField().getValue());
		if (optionsButton.isDown() != advancedSearch) optionsButton.setDown(advancedSearch);
		if (optionsPanel.isOpen() != advancedSearch) optionsPanel.setOpen(advancedSearch);
		searchField.setVisible(!advancedSearch);
	}
	
	private TextBox getVisibleSearchField () {
		if (optionsPanel.isOpen()) return advancedSearchField;
		else return searchField;
	}
	
	private TextBox getHiddenTextField () {
		if (optionsPanel.isOpen()) return searchField;
		else return advancedSearchField;
	}
	
	@Override
	public String getValue () {
		return getVisibleSearchField().getValue();
	}
	
	@Override
	public void setValue (String value) {
		setValue(value, false);
	}

	@Override
	public void setValue (String value, boolean fireEvents) {
		getVisibleSearchField().setValue(value, fireEvents);
	}

	@Override
	public void setFocus (boolean focused) {
		getVisibleSearchField().setFocus(focused);
	}

}
