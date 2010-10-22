package ca.ualberta.cs.courseplanner.client.search;

import ca.ualberta.cs.courseplanner.client.activity.PlaceAwareActivity;
import ca.ualberta.cs.courseplanner.client.course.CoursePlace;
import ca.ualberta.cs.courseplanner.client.intents.CreatePlanIntent;
import ca.ualberta.cs.courseplanner.client.search.place.SavedSearchPlace;
import ca.ualberta.cs.courseplanner.client.search.place.SearchPlace;
import ca.ualberta.cs.courseplanner.client.search.place.SearchQueryPlace;
import ca.ualberta.cs.courseplanner.model.CourseDetails;
import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;
import ca.ualberta.cs.courseplanner.model.Search;
import ca.ualberta.cs.courseplanner.services.CourseDataServiceAsync;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

public class SearchActivity extends AbstractActivity implements PlaceAwareActivity {
	
	interface Binder extends UiBinder<IsWidget, SearchActivity> { }
	
	interface Style extends CssResource {
		String resultsTable();
		String resultsTableCollapsed();
	}
	
	private static final Binder BINDER = GWT.create(Binder.class);
	
	private final MultiSelectionModel<CourseInfo> multiSelectionModel =
		new MultiSelectionModel<CourseInfo>(CourseInfo.KEY_PROVIDER);
	
	private final SingleSelectionModel<CourseInfo> singleSelectionModel =
		new SingleSelectionModel<CourseInfo>(CourseInfo.KEY_PROVIDER);
	
	private final CourseDataServiceAsync courseDataService;
	private final ResultsProvider resultsProvider;
	
	private CourseInfo course;
	
	@UiField(provided=true) final ResultsTable resultsTable = new ResultsTable(multiSelectionModel);
	
	@UiField Style style;
	
	@UiField Anchor closeCourseLink;
	@UiField HTMLPanel courseDetails;
	@UiField Label courseName;
	@UiField Label courseOrg1;
	@UiField Label courseOrg2;
	@UiField Label courseDescription;
	
	@Inject
	public SearchActivity (
			CourseDataServiceAsync courseDataService,
			UserDataServiceAsync userDataService
	) {
		this.courseDataService = courseDataService;
		this.resultsProvider = new ResultsProvider(courseDataService, userDataService);
	}
	
	@Override
	public void start (AcceptsOneWidget display, EventBus eventBus) {
		resultsProvider.addDataDisplay(resultsTable);
		display.setWidget(BINDER.createAndBindUi(this));
	}
	
	public void setCourse (CourseInfo course) {
		this.course = course;
		if (course == null) {
			resultsTable.setCollapsed(false);
			resultsTable.addStyleName(style.resultsTable());
			resultsTable.removeStyleName(style.resultsTableCollapsed());
			resultsTable.setSelectionModel(multiSelectionModel);
			closeCourseLink.setVisible(false);
			courseDetails.setVisible(false);
		}
		else {
			resultsTable.setCollapsed(true);
			resultsTable.addStyleName(style.resultsTableCollapsed());
			resultsTable.removeStyleName(style.resultsTable());
			resultsTable.setSelectionModel(singleSelectionModel);
			singleSelectionModel.setSelected(course, true);
			closeCourseLink.setVisible(true);
			showCourse();
		}
	}

	@Override
	public boolean setNewPlace (Place place) {
		if (place instanceof SearchPlace) return setNewPlace ((SearchPlace)place);
		if (place instanceof CoursePlace) return setNewPlace ((CoursePlace)place);
		else return false;
	}
	
	public boolean setNewPlace (SearchPlace place) {
		boolean result = false;
		if (place instanceof SearchQueryPlace) result = setNewPlace ((SearchQueryPlace)place);
		else if (place instanceof SavedSearchPlace) result = setNewPlace ((SavedSearchPlace)place);
		// TODO set page number and course
		return result;
	}
	
	public boolean setNewPlace (SearchQueryPlace place) {
		Search search = place.getSearch();
		if (resultsProvider.getSearchInfo() == null
				&& (resultsProvider.getSearch() == null
						|| resultsProvider.getSearch().getQuery().equals(search.getQuery()))) {
			resultsProvider.setSearch(search);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean setNewPlace (SavedSearchPlace place) {
		SavedSearchInfo savedSearch = place.getSavedSearch();
		if (resultsProvider.getSearchInfo() != null
				&& resultsProvider.getSearchInfo().getId() == savedSearch.getId()) {
			resultsProvider.setSearchInfo(savedSearch);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean setNewPlace (CoursePlace place) {
		setCourse (place.getCourse());
		return true;
	}

	@UiHandler("closeCourseLink")
	void handleCloseCourse (ClickEvent event) {
		setCourse(null);
	}

	void showCourse () {
		courseDataService.getCourseDetails(course.getId(), new AsyncCallback<CourseDetails>() {
			@Override
			public void onSuccess (CourseDetails details) {
				courseName.setText(details.getSubjectId()+" "+details.getNumber()+" "+details.getName());
				courseOrg1.setText(details.getOrg1Name());
				courseOrg2.setText(details.getOrg2Name());
				courseDescription.setText(details.getDescription());
				courseDetails.setVisible(true);
			}
			@Override
			public void onFailure (Throwable caught) {
				setCourse(null);
			}
		});
	}

}
