package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.presenter.Presenter;
import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.services.CourseDataServiceAsync;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;


public class MainPresenter implements Presenter, ValueChangeHandler<String> {
	
	private CourseDataServiceAsync courseDataService;	
	private EventBus eventBus;
	private HasWidgets container;
	
	public MainPresenter (CourseDataServiceAsync courseDataService, EventBus eventBus) {
		this.courseDataService = courseDataService;
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind () {
		History.addValueChangeHandler(this);
	}

	@Override
	public void present (HasWidgets container) {
		this.container = container;
		History.fireCurrentHistoryState();
	}

	@Override
	public void onValueChange (ValueChangeEvent<String> event) {
		
		String[] tokens = event.getValue().split(":", 2);

		if (tokens.length >= 2 && tokens[0].equals("course")) {
			
			Long id = Long.parseLong(tokens[1]);
			
			courseDataService.getCourseDetails(id,
					new AsyncCallback<CourseDetails>() {
						
						@Override
						public void onSuccess (CourseDetails result) {
							if (result == null) return;
							container.clear();
							container.add(new HTML(
									""
									+ result.getSubjectId() + " "
									+ result.getNumber() + " "
									+ result.getName() + "<br>"
									+ "<i>" + result.getOrg1Name() + "</i><br/>"
									+ "<i>" + result.getOrg2Name() + "</i><br/>"
									+ "<p>" + result.getDescription() + "</p>"
							));
						}
						
						@Override
						public void onFailure (Throwable caught) {
							Window.alert(caught.getMessage());
						}
					});
		}
	}

}
