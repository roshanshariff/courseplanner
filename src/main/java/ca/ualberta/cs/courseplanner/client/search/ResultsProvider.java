package ca.ualberta.cs.courseplanner.client.search;

import java.util.Arrays;
import java.util.Collections;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchDetails;
import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchResults;
import ca.ualberta.cs.courseplanner.model.Search;
import ca.ualberta.cs.courseplanner.model.SearchResults;
import ca.ualberta.cs.courseplanner.services.CourseDataServiceAsync;
import ca.ualberta.cs.courseplanner.services.UserDataServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;


public class ResultsProvider extends AsyncDataProvider<CourseInfo> implements AsyncCallback<SearchResults> {
	
	private final CourseDataServiceAsync courseDataService;

	private final UserDataServiceAsync userDataService;

	private Search search;
	
	private SavedSearchInfo searchInfo;

	public ResultsProvider (
			CourseDataServiceAsync courseDataService,
			UserDataServiceAsync userDataService
	) {
		super(CourseInfo.KEY_PROVIDER);
		this.courseDataService = courseDataService;
		this.userDataService = userDataService;
	}
	
	public Search getSearch () {
		return search;
	}
	
	public void setSearch (Search search) {
		this.search = search;
	}

	public SavedSearchInfo getSearchInfo () {
		return searchInfo;
	}

	protected void setSearchInfo (SavedSearchInfo searchInfo) {
		this.searchInfo = searchInfo;
		if (searchInfo != null) {
			setSearch (null);
			if (searchInfo instanceof SavedSearchDetails) {
				setSearch (((SavedSearchDetails)searchInfo).getSearch());
			}
		}
	}

	@Override
	protected void onRangeChanged (HasData<CourseInfo> display) {
		final Range range = display.getVisibleRange();
		if (getSearch() != null) {
			courseDataService.searchCourses(getSearch(), range.getStart(), range.getLength(), this);
		}
		else if (getSearchInfo() != null) {
			userDataService.getSavedSearchResults(
					getSearchInfo().getId(), range.getStart(), range.getLength(),
					new AsyncCallback<SavedSearchResults>() {
						@Override
						public void onSuccess (SavedSearchResults savedSearchResults) {
							SearchResults results = savedSearchResults.getResults();
							savedSearchResults.setResults(null);
							ResultsProvider.this.setSearchInfo(savedSearchResults);
							ResultsProvider.this.onSuccess (results);
						}
						@Override
						public void onFailure (Throwable caught) {
							ResultsProvider.this.onFailure(caught);
						}
					}
			);
		}
		else {
			ResultsProvider.this.onFailure(new IllegalStateException("Uninitialied ResultsProvider"));
		}
	}

	@Override
	public void onSuccess (SearchResults results) {
		updateRowCount(results.getNumResults(), results.isNumResultsExact());
		updateRowData(results.getFirstResult(), Arrays.asList(results.getResults()));
	}

	@Override
	public void onFailure (Throwable caught) {
		updateRowCount(0, true);
		updateRowData(0, Collections.<CourseInfo>emptyList());
	}

}
