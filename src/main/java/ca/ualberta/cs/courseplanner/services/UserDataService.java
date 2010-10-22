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

import com.google.gwt.rpc.client.RpcService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../services/userdata")
public interface UserDataService extends RpcService {
	
	PlanDetails createPlan (String planName);
	
	PlanDetails getPlanDetails (long planId);
	
	PlanCourses getPlanCourses (long planId);

	PlanInfo deletePlan (long planId);
	
	PlanInfo[] getPlans ();
	
	PlanInfo setPlanName (long planId, String planName);
	
	PlanDetails setPlanDescription (long planId, String description);
	
	PlanDetails setPlanPublic (long planId, boolean isPublic);
	
	void addPlanCourse (long planId, long courseId, CoursePlanInfo info);
	
	void addPlanCourses (long planId, long[] courseIds);
	
	void removePlanCourse (long planId, long courseId);
	
	
	void addTranscriptCourse (long courseId, CourseTranscriptInfo info);
	
	void addTranscriptCourses (long[] courseIds);
	
	void removeTranscriptCourse (long courseId);
	
	Map<CourseInfo, CourseTranscriptInfo> getTranscriptCourses ();
	
	
	SavedSearchDetails createSavedSearch (String searchName, Search search);
	
	SavedSearchDetails getSavedSearchDetails (long searchId);

	SavedSearchResults getSavedSearchResults (long searchId, int firstResult, int maxResults);
	
	SavedSearchInfo deleteSavedSearch (long searchId);
	
	SavedSearchInfo[] getSavedSearches ();
	
	SavedSearchInfo setSavedSearchName (long searchId, String name);
	
	SavedSearchDetails setSavedSearchDescription (long searchId, String description);
	
	SavedSearchDetails setSavedSearchPublic (long searchId, boolean isPublic);
	
}
