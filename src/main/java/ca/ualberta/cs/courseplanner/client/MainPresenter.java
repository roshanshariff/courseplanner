package ca.ualberta.cs.courseplanner.client;

import ca.ualberta.cs.courseplanner.client.presenter.Presenter;
import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


public class MainPresenter implements Presenter, ValueChangeHandler<String> {
	
	private CourseDataServiceAsync courseDataService;	
	private HandlerManager eventBus;
	private HasWidgets container;
	
	public MainPresenter (CourseDataServiceAsync courseDataService, HandlerManager eventBus) {
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
			
			String id = tokens[1];
			
			courseDataService.getCourseDetails(id,
					new AsyncCallback<CourseDetails>() {
						
						@Override
						public void onSuccess (CourseDetails result) {
							container.clear();
							container.add(new HTML(
									"<h1>"
									+ result.getSubjectId() + " "
									+ result.getNumber() + " "
									+ result.getTitle() + "</h1>"
									+ "<i>" + result.getOrg1Name() + "</i><br/>"
									+ "<i>" + result.getOrg2Name() + "</i><br/>"
									+ "<p>" + result.getDescription() + "</p>"
							));
						}
						
						@Override
						public void onFailure (Throwable caught) {
							RootPanel.getBodyElement().setInnerText(caught.getMessage());
						}
					});
		}
	}

}
