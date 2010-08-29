package ca.ualberta.cs.courseplanner.services;

import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CoursePlanInfo;
import ca.ualberta.cs.courseplanner.model.PlanInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../services/userdata")
public interface UserDataService extends RemoteService {
	
	PlanInfo createPlan (String planName);
	
	PlanInfo getPlanInfo (long planId);

	PlanInfo[] getPlans ();
	
	void deletePlan (long planId);
	
	void setPlanName (long planId, String planName);
	
	void addPlanCourse (long planId, long courseId);
	
	void addPlanCourse (long planId, long courseId, CoursePlanInfo info);
	
	void addPlanCourses (long planId, long[] courseIds);
	
	CoursePlanInfo getPlanCourse (long planId, long courseId);
	
	Map<CourseInfo, CoursePlanInfo> getPlanCourses (long planId);
	
	void removePlanCourse (long planId, long courseId);
	
}
