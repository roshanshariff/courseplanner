package ca.ualberta.cs.courseplanner.services;

import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CoursePlanInfo;
import ca.ualberta.cs.courseplanner.model.PlanInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserDataServiceAsync {

	void createPlan(String planName, AsyncCallback<PlanInfo> callback);

	void getPlanInfo(long planId, AsyncCallback<PlanInfo> callback);

	void getPlans(AsyncCallback<PlanInfo[]> callback);

	void deletePlan(long planId, AsyncCallback<Void> callback);

	void setPlanName(long planId, String planName, AsyncCallback<Void> callback);

	void addPlanCourse(long planId, long courseId, AsyncCallback<Void> callback);

	void addPlanCourse(long planId, long courseId, CoursePlanInfo info,
			AsyncCallback<Void> callback);

	void addPlanCourses (long planId, long[] courseIds,
			AsyncCallback<Void> callback);

	void getPlanCourse(long planId, long courseId,
			AsyncCallback<CoursePlanInfo> callback);

	void getPlanCourses(long planId,
			AsyncCallback<Map<CourseInfo, CoursePlanInfo>> callback);

	void removePlanCourse(long planId, long courseId,
			AsyncCallback<Void> callback);

}
