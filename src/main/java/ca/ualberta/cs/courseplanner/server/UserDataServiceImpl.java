package ca.ualberta.cs.courseplanner.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.Course;
import ca.ualberta.cs.courseplanner.entities.Plan;
import ca.ualberta.cs.courseplanner.entities.User;
import ca.ualberta.cs.courseplanner.model.*;
import ca.ualberta.cs.courseplanner.services.UserDataService;


public class UserDataServiceImpl implements UserDataService {

	private DataRepository dataRepository;
	
	private Mapper mapper;
	
	private UserIdProvider userIdProvider;
	
	public void setDataRepository (DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}
	
	public void setMapper (Mapper mapper) {
		this.mapper = mapper;
	}
	
	public void setUserIdProvider (UserIdProvider user) {
		this.userIdProvider = user;
	}
	
	private User getCurrentUser () {
		return dataRepository.getUser(userIdProvider.getUserId());
	}
	
	private Plan getPlan (Long planId) {
		Plan plan = dataRepository.getPlan(planId);
		if (plan.getUser().getId().equals(userIdProvider.getUserId())) {
			throw new IllegalArgumentException ("Invalid plan identifier.");
		}
		return plan;
	}

	@Override
	@Transactional
	public PlanInfo createPlan(String planName) {
		return mapper.map(dataRepository.createPlan(getCurrentUser(), planName), PlanInfo.class);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanInfo[] getPlans () {
		List<PlanInfo> plans = new ArrayList<PlanInfo>();
		for (Plan plan : dataRepository.getUserPlans(userIdProvider.getUserId())) {
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
		dataRepository.deletePlan(getPlan(planId));
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
		getPlan(planId).getCourses().put(dataRepository.getCourse(courseId), info);
	}

	@Override
	@Transactional
	public void addPlanCourses (long planId, long[] courseIds) {
		Map<Course, CoursePlanInfo> planCourses = getPlan(planId).getCourses();
		for (long courseId : courseIds) {
			planCourses.put(dataRepository.getCourse(courseId), new CoursePlanInfo());
		}
	}

	@Override
	@Transactional(readOnly=true)
	public CoursePlanInfo getPlanCourse(long planId, long courseId) {
		return getPlan(planId).getCourses().get(dataRepository.getCourse(courseId));
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
		getPlan(planId).getCourses().remove(dataRepository.getCourse(courseId));
	}

}
