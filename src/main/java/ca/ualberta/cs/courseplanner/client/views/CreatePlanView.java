package ca.ualberta.cs.courseplanner.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;


public class CreatePlanView {
	
	interface Binder extends UiBinder<DialogBox, CreatePlanView> { }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	@UiField DialogBox dialog;
	@UiField FormPanel form;
	
	@UiField TextBox planName;	
	@UiField FocusWidget createButton;
	
	
	public CreatePlanView () {
		binder.createAndBindUi(this);
		dialog.center();
		planName.setFocus(true);
	}
	
	@UiHandler("planName")
	void handleChange (ChangeEvent event) {
		createButton.setEnabled(!planName.getValue().isEmpty());
	}
	
	@UiHandler("form")
	void handleCreate (FormPanel.SubmitEvent event) {
		if (!planName.getValue().isEmpty()) {
			Window.alert("Creating plan "+planName.getValue());
		}
		event.cancel();
	}
	
	@UiHandler("createButton")
	void handleCreate (ClickEvent event) {
		form.submit();
	}
	
	@UiHandler("cancelButton")
	void handleCancel (ClickEvent event) {
		dialog.hide();
	}

}
