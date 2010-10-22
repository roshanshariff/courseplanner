package ca.ualberta.cs.courseplanner.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class PromptDialogView implements IsWidget, HasText, HasValue<String> {
	
	public static interface Presenter {
		
		void onSubmit ();
		
		void onCancel ();
		
	}
	
	interface Binder extends UiBinder<DialogBox, PromptDialogView> { }
	
	private static final Binder BINDER = GWT.create(Binder.class);
	
	private Presenter presenter;
	
	@UiField DialogBox dialog;
	@UiField Label prompt;
	@UiField TextBox input;	
	@UiField Button submit;
	@UiField Button cancel;
	
	public PromptDialogView () {
		BINDER.createAndBindUi(this);
	}
	
	public void setPresenter (Presenter presenter) {
		this.presenter = presenter;
	}
	
	public void show () {
		dialog.center();
		input.setFocus(true);
	}
	
	public void hide () {
		dialog.hide();
	}
	
	public String getCaption () {
		return dialog.getText();
	}
	
	public void setCaption (String text) {
		dialog.setText(text);
	}
	
	public Button getSubmitButton () {
		return submit;
	}
	
	public Button getCancelButton () {
		return cancel;
	}
	
	@UiHandler("input")
	void onInputKey (KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			submit.click();
		}
		else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
			cancel.click();
		}
	}
	
	@UiHandler("submit")
	void onSubmitClick (ClickEvent event) {
		doSubmit();
	}
	
	@UiHandler("cancel")
	void onCancelClick (ClickEvent event) {
		doCancel();
	}
	
	private void doSubmit () {
		DeferredCommand.addCommand(new Command() {
			@Override
			public void execute () {
				if (presenter != null) presenter.onSubmit();
			}
		});
	}
	
	private void doCancel () {
		DeferredCommand.addCommand(new Command() {
			@Override
			public void execute () {
				if (presenter != null) presenter.onCancel();
			}
		});
	}

	@Override
	public HandlerRegistration addValueChangeHandler (ValueChangeHandler<String> handler) {
		return input.addValueChangeHandler(handler);
	}

	@Override
	public String getValue () {
		return input.getValue();
	}
	
	@Override
	public void setValue (String text) {
		input.setValue(text);
	}
	
	@Override
	public void setValue (String value, boolean fireEvents) {
		input.setValue(value, fireEvents);
	}

	@Override
	public String getText () {
		return prompt.getText();
	}

	@Override
	public void setText (String text) {
		prompt.setText(text);
	}

	@Override
	public void fireEvent (GwtEvent<?> event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget asWidget () {
		return dialog;
	}

}
