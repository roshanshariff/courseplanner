package ca.ualberta.cs.courseplanner.services;

import java.util.Map;

import ca.ualberta.cs.courseplanner.dto.CourseInfo;
import ca.ualberta.cs.courseplanner.dto.CoursePlanInfo;
import ca.ualberta.cs.courseplanner.dto.PlanInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userdata")
public interface UserDataService extends RemoteService {
	
	PlanInfo createPlan (String planName);
	
	PlanInfo getPlanInfo (long planId);

	PlanInfo[] getPlans ();
	
	void deletePlan (long planId);
	
	void setPlanName (long planId, String planName);
	
	void addPlanCourse (long planId, long courseId);
	
	void addPlanCourse (long planId, long courseId, CoursePlanInfo info);
	
	CoursePlanInfo getPlanCourse (long planId, long courseId);
	
	Map<CourseInfo, CoursePlanInfo> getPlanCourses (long planId);
	
	void removePlanCourse (long planId, long courseId);
	
}
