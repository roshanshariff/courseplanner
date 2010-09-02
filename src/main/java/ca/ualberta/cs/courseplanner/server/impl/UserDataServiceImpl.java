package ca.ualberta.cs.courseplanner.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.Course;
import ca.ualberta.cs.courseplanner.entities.Plan;
import ca.ualberta.cs.courseplanner.entities.User;
import ca.ualberta.cs.courseplanner.model.*;
import ca.ualberta.cs.courseplanner.server.services.DataRepository;
import ca.ualberta.cs.courseplanner.server.services.UserIdProvider;
import ca.ualberta.cs.courseplanner.services.UserDataService;


@Service
@Singleton
public class UserDataServiceImpl implements UserDataService {

	private final DataRepository dataRepository;
	
	private final Mapper mapper;
	
	private final UserIdProvider currentUserId;
	
	private final Provider<User> currentUser;
	
	
	@Inject
	public UserDataServiceImpl (DataRepository dataRepository, Mapper mapper,
			UserIdProvider currentUserId) {
		this.dataRepository = dataRepository;
		this.mapper = mapper;
		this.currentUserId = currentUserId;
		this.currentUser = new CurrentUserProvider(currentUserId, dataRepository);
	}

	
	private Plan getPlan (Long planId) {
		Plan plan = dataRepository.getPlan(planId);
		if (plan.getUser().getId().equals(currentUserId.getUserId())) {
			throw new IllegalArgumentException ("Invalid plan identifier.");
		}
		return plan;
	}

	@Override
	@Transactional
	public PlanInfo createPlan(String planName) {
		return mapper.map(dataRepository.createPlan(currentUser.get(), planName), PlanInfo.class);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanInfo[] getPlans () {
		List<PlanInfo> plans = new ArrayList<PlanInfo>();
		for (Plan plan : dataRepository.getUserPlans(currentUserId.getUserId())) {
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
