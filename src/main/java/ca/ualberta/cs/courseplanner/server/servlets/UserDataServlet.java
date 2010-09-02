package ca.ualberta.cs.courseplanner.server.servlets;

import java.util.Map;

import ca.ualberta.cs.courseplanner.model.CourseInfo;
import ca.ualberta.cs.courseplanner.model.CoursePlanInfo;
import ca.ualberta.cs.courseplanner.model.PlanInfo;
import ca.ualberta.cs.courseplanner.services.UserDataService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class UserDataServlet extends RemoteServiceServlet implements UserDataService {
	
	private static final long serialVersionUID = -987127814153552667L;

	private final UserDataService service;
	
	public UserDataServlet (UserDataService service) {
		this.service = service;
	}
	
	@Override
	public PlanInfo createPlan (String planName) {
		return service.createPlan(planName);
	}

	@Override
	public PlanInfo getPlanInfo (long planId) {
		return service.getPlanInfo(planId);
	}

	@Override
	public PlanInfo[] getPlans () {
		return service.getPlans();
	}

	@Override
	public void deletePlan (long planId) {
		service.deletePlan(planId);
	}

	@Override
	public void setPlanName (long planId, String planName) {
		service.setPlanName(planId, planName);
	}

	@Override
	public void addPlanCourse (long planId, long courseId) {
		service.addPlanCourse(planId, courseId);
	}

	@Override
	public void addPlanCourse (long planId, long courseId, CoursePlanInfo info) {
		service.addPlanCourse(planId, courseId, info);
	}

	@Override
	public void addPlanCourses (long planId, long[] courseIds) {
		service.addPlanCourses(planId, courseIds);
	}

	@Override
	public CoursePlanInfo getPlanCourse (long planId, long courseId) {
		return service.getPlanCourse(planId, courseId);
	}

	@Override
	public Map<CourseInfo, CoursePlanInfo> getPlanCourses (long planId) {
		return service.getPlanCourses(planId);
	}

	@Override
	public void removePlanCourse (long planId, long courseId) {
		service.removePlanCourse(planId, courseId);
	}

}
