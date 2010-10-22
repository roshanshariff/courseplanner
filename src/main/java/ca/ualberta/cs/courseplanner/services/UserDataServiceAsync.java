package ca.ualberta.cs.courseplanner.services;

import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CoursePlanInfo;
import ca.ualberta.cs.courseplanner.model.CourseTranscriptInfo;
import ca.ualberta.cs.courseplanner.model.PlanCourses;
import ca.ualberta.cs.courseplanner.model.PlanDetails;
import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchDetails;
import ca.ualberta.cs.courseplanner.model.SavedSearchInfo;
import ca.ualberta.cs.courseplanner.model.SavedSearchResults;
import ca.ualberta.cs.courseplanner.model.Search;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface UserDataServiceAsync {

	void addPlanCourse (long planId, long courseId, CoursePlanInfo info,
			AsyncCallback<Void> callback);

	void addPlanCourses (long planId, long[] courseIds,
			AsyncCallback<Void> callback);

	void addTranscriptCourse (long courseId, CourseTranscriptInfo info,
			AsyncCallback<Void> callback);

	void addTranscriptCourses (long[] courseIds, AsyncCallback<Void> callback);

	void createPlan (String planName, AsyncCallback<PlanDetails> callback);

	void createSavedSearch (String searchName, Search search,
			AsyncCallback<SavedSearchDetails> callback);

	void deletePlan (long planId, AsyncCallback<PlanInfo> callback);

	void deleteSavedSearch (long searchId,
			AsyncCallback<SavedSearchInfo> callback);

	void getPlanCourses (long planId, AsyncCallback<PlanCourses> callback);

	void getPlanDetails (long planId, AsyncCallback<PlanDetails> callback);

	void getPlans (AsyncCallback<PlanInfo[]> callback);

	void getSavedSearchDetails (long searchId,
			AsyncCallback<SavedSearchDetails> callback);

	void getSavedSearchResults (long searchId, int firstResult, int maxResults,
			AsyncCallback<SavedSearchResults> callback);

	void getSavedSearches (AsyncCallback<SavedSearchInfo[]> callback);

	void removePlanCourse (long planId, long courseId,
			AsyncCallback<Void> callback);

	void removeTranscriptCourse (long courseId, AsyncCallback<Void> callback);

	void setPlanDescription (long planId, String description,
			AsyncCallback<PlanDetails> callback);

	void setPlanName (long planId, String planName,
			AsyncCallback<PlanInfo> callback);

	void setPlanPublic (long planId, boolean isPublic,
			AsyncCallback<PlanDetails> callback);

	void setSavedSearchDescription (long searchId, String description,
			AsyncCallback<SavedSearchDetails> callback);

	void setSavedSearchName (long searchId, String name,
			AsyncCallback<SavedSearchInfo> callback);

	void setSavedSearchPublic (long searchId, boolean isPublic,
			AsyncCallback<SavedSearchDetails> callback);

	void getTranscriptCourses (
			AsyncCallback<Map<CourseInfo, CourseTranscriptInfo>> callback);

}
