package ca.ualberta.cs.courseplanner.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;


public class PromptDialogView {
	
	public static interface Presenter {
		
		void onSubmit ();
		
		void onCancel ();
		
	}
	
	interface Binder extends UiBinder<DialogBox, PromptDialogView> { }
	
	private static final Binder binder = GWT.create(Binder.class);
	
	private Presenter presenter;
	
	@UiField DialogBox dialog;
	@UiField Label prompt;
	@UiField TextBox input;	
	@UiField Button submit;
	@UiField Button cancel;
	
	
	public PromptDialogView () {
		binder.createAndBindUi(this);
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
	
	public void setCaption (String text) {
		dialog.setText(text);
	}
	
	public void setPrompt (String text) {
		prompt.setText(text);
	}
	
	public void setSubmitText (String text) {
		submit.setText(text);
	}
	
	public void setCancelText (String text) {
		cancel.setText(text);
	}
	
	public void setValue (String text) {
		input.setValue(text);
	}
	
	public String getValue () {
		return input.getValue();
	}
	
	@UiHandler("input")
	void onInputKey (KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			doSubmit();
		}
		else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
			doCancel();
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

}
