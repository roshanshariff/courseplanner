package ca.ualberta.cs.courseplanner.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.domain.Plan;
import ca.ualberta.cs.courseplanner.domain.User;
import ca.ualberta.cs.courseplanner.dto.*;
import ca.ualberta.cs.courseplanner.services.UserDataService;


public class UserDataServiceImpl implements UserDataService {

	private DataRepository data;
	
	private UserProvider currentUser;
	
	private Mapper mapper;
	
	public void setData (DataRepository data) {
		this.data = data;
	}
	
	public void setUserProvider (UserProvider user) {
		this.currentUser = user;
	}
	
	public void setMapper (Mapper mapper) {
		this.mapper = mapper;
	}
	
	private User getCurrentUser () {
		return data.getUser(currentUser.getUserId());
	}
	
	private Plan getPlan (Long planId) {
		Plan plan = data.getPlan(planId);
		if (plan.getUser().getId().equals(currentUser.getUserId())) {
			throw new IllegalArgumentException ("Invalid plan identifier.");
		}
		return plan;
	}

	@Override
	@Transactional
	public PlanInfo createPlan(String planName) {
		return mapper.map(data.createPlan(getCurrentUser(), planName), PlanInfo.class);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanInfo[] getPlans () {
		List<PlanInfo> plans = new ArrayList<PlanInfo>();
		for (Plan plan : getCurrentUser().getPlans()) {
			plans.add(mapper.map(plan, PlanInfo.class));
		}
		return plans.toArray(new PlanInfo[plans.size()]);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanInfo getPlanInfo (long planId) {
		return mapper.map(getPlan(planId), PlanInfo.class);
	}

	@Override
	@Transactional
	public void deletePlan(long planId) {
		data.deletePlan(getPlan(planId));
	}

	@Override
	@Transactional
	public void setPlanName(long planId, String planName) {
		getPlan(planId).setName(planName);
	}

	@Override
	@Transactional
	public void addPlanCourse(long planId, long courseId) {
		addPlanCourse(planId, courseId, new CoursePlanInfo());
	}

	@Override
	@Transactional
	public void addPlanCourse(long planId, long courseId, CoursePlanInfo info) {
		getPlan(planId).getCourses().put(data.getCourse(courseId), info);
	}

	@Override
	@Transactional(readOnly=true)
	public CoursePlanInfo getPlanCourse(long planId, long courseId) {
		return getPlan(planId).getCourses().get(data.getCourse(courseId));
	}

	@Override
	@Transactional(readOnly=true)
	public Map<CourseInfo, CoursePlanInfo> getPlanCourses(long planId) {
		Map<CourseInfo, CoursePlanInfo> result = new HashMap<CourseInfo, CoursePlanInfo>();
		for (Map.Entry<Course, CoursePlanInfo> entry : getPlan(planId).getCourses().entrySet()) {
			result.put(mapper.map(entry.getKey(), CourseInfo.class), entry.getValue());
		}
		return result;
	}

	@Override
	@Transactional
	public void removePlanCourse(long planId, long courseId) {
		getPlan(planId).getCourses().remove(data.getCourse(courseId));
	}

}