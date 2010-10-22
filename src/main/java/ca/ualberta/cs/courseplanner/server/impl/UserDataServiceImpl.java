package ca.ualberta.cs.courseplanner.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.dozer.Mapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.entities.Course;
import ca.ualberta.cs.courseplanner.entities.Plan;
import ca.ualberta.cs.courseplanner.entities.SavedSearch;
import ca.ualberta.cs.courseplanner.entities.User;
import ca.ualberta.cs.courseplanner.entities.UserObject;
import ca.ualberta.cs.courseplanner.model.*;
import ca.ualberta.cs.courseplanner.server.services.DataRepository;
import ca.ualberta.cs.courseplanner.server.services.SearchEngine;
import ca.ualberta.cs.courseplanner.services.UserDataService;


@Service
@Singleton
@Named("userDataService")
public class UserDataServiceImpl implements UserDataService {

	private final DataRepository dataRepository;
	
	private final Mapper mapper;
	
	private final SearchEngine searchEngine;
	
	private final Provider<String> currentUserId;
	
	private final Provider<User> currentUser;
	
	
	@Inject
	public UserDataServiceImpl (DataRepository dataRepository, Mapper mapper,
			SearchEngine searchEngine,
			@Named("currentUserId") Provider<String> currentUserId,
			@Named("currentUser") Provider<User> currentUser) {
		this.dataRepository = dataRepository;
		this.mapper = mapper;
		this.searchEngine = searchEngine;
		this.currentUserId = currentUserId;
		this.currentUser = currentUser;
	}
	
	private void prepareAccess (String userId) {
		if (!userId.equals(currentUserId.get())) {
			throw new AccessDeniedException("User "+currentUserId.get()+" has insufficient access.");
		}
	}
	
	private void prepareRead (UserObject object) {
		if (!object.isPublic()) {
			prepareAccess (object.getUser().getId());
		}
	}
	
	private void prepareWrite (UserObject object) {
		prepareAccess (object.getUser().getId());
	}

	@Override
	@Transactional
	public PlanDetails createPlan(String planName) {
		// TODO Ensure mapping data is working
		Plan plan = dataRepository.createPlan(currentUser.get(), planName);
		return mapper.map(plan, PlanDetails.class);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanDetails getPlanDetails (long planId) {
		Plan plan = dataRepository.getPlan(planId);
		prepareRead(plan);
		return mapper.map(plan, PlanDetails.class);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanCourses getPlanCourses(long planId) {
		Plan plan = dataRepository.getPlan(planId);
		prepareRead(plan);
		return mapper.map(plan, PlanCourses.class);
	}

	@Override
	@Transactional
	public PlanInfo deletePlan(long planId) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		dataRepository.deletePlan(plan);
		return mapper.map(plan, PlanInfo.class);
	}

	@Override
	@Transactional(readOnly=true)
	public PlanInfo[] getPlans () {
		List<PlanInfo> plans = new ArrayList<PlanInfo>();
		for (Plan plan : dataRepository.getUserPlans(currentUserId.get())) {
			plans.add(mapper.map(plan, PlanInfo.class));
		}
		return plans.toArray(new PlanInfo[plans.size()]);
	}

	@Override
	@Transactional
	public PlanInfo setPlanName(long planId, String planName) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		plan.setName(planName);
		return mapper.map(plan, PlanInfo.class);
	}

	@Override
	@Transactional
	public PlanDetails setPlanDescription (long planId, String description) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		plan.setDescription(description);
		return mapper.map(plan, PlanDetails.class);
	}

	@Override
	@Transactional
	public PlanDetails setPlanPublic (long planId, boolean isPublic) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		plan.setPublic(isPublic);
		return mapper.map(plan, PlanDetails.class);
	}

	@Override
	@Transactional
	public void addPlanCourse(long planId, long courseId, CoursePlanInfo info) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		plan.getCourses().put(dataRepository.getCourse(courseId), info);
	}

	@Override
	@Transactional
	public void addPlanCourses (long planId, long[] courseIds) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		Map<Course, CoursePlanInfo> planCourses = plan.getCourses();
		for (long courseId : courseIds) {
			planCourses.put(dataRepository.getCourse(courseId), new CoursePlanInfo());
		}
	}

	@Override
	@Transactional
	public void removePlanCourse(long planId, long courseId) {
		Plan plan = dataRepository.getPlan(planId);
		prepareWrite(plan);
		Course course = dataRepository.getCourse(courseId);
		plan.getCourses().remove(course);
	}

	@Override
	@Transactional
	public void addTranscriptCourse (long courseId, CourseTranscriptInfo info) {
		User user = currentUser.get();
		user.getCourseHistory().put(dataRepository.getCourse(courseId), info);
	}

	@Override
	@Transactional
	public void addTranscriptCourses (long[] courseIds) {
		User user = currentUser.get();
		Map<Course, CourseTranscriptInfo> transcriptCourses = user.getCourseHistory();
		for (long courseId : courseIds) {
			transcriptCourses.put(dataRepository.getCourse(courseId), new CourseTranscriptInfo());
		}
	}

	@Override
	@Transactional
	public void removeTranscriptCourse (long courseId) {
		User user = currentUser.get();
		Course course = dataRepository.getCourse(courseId);
		user.getCourseHistory().remove(course);
	}

	@Override
	@Transactional(readOnly=true)
	public Map<CourseInfo, CourseTranscriptInfo> getTranscriptCourses () {
		Map<Course, CourseTranscriptInfo> source = currentUser.get().getCourseHistory();
		Map<CourseInfo, CourseTranscriptInfo> result = new HashMap<CourseInfo, CourseTranscriptInfo>();
		for (Map.Entry<Course, CourseTranscriptInfo> entry : source.entrySet()) {
			CourseInfo courseInfo = mapper.map(entry.getKey(), CourseInfo.class);
			CourseTranscriptInfo courseTranscriptInfo = mapper.map(entry.getValue(), CourseTranscriptInfo.class);
			result.put(courseInfo, courseTranscriptInfo);
		}
		return result;
	}



	@Override
	@Transactional
	public SavedSearchDetails createSavedSearch (String searchName, Search search) {
		// TODO Ensure mapping data is working
		SavedSearch savedSearch = dataRepository.createSavedSearch(currentUser.get(), searchName, search);
		return mapper.map(savedSearch, SavedSearchDetails.class);
	}


	@Override
	@Transactional(readOnly=true)
	public SavedSearchDetails getSavedSearchDetails (long searchId) {
		SavedSearch search = dataRepository.getSavedSearch(searchId);
		prepareRead(search);
		return mapper.map(search, SavedSearchDetails.class);
	}


	@Override
	@Transactional(readOnly=true)
	public SavedSearchResults getSavedSearchResults (long searchId, int firstResult, int maxResults) {
		SavedSearch savedsearch = dataRepository.getSavedSearch(searchId);
		prepareRead(savedsearch);
		SavedSearchResults results = mapper.map(savedsearch, SavedSearchResults.class);
		results.setResults(searchEngine.searchCourses(savedsearch.getSearch(), firstResult, maxResults));
		return results;
	}


	@Override
	@Transactional
	public SavedSearchInfo deleteSavedSearch (long searchId) {
		SavedSearch search = dataRepository.getSavedSearch(searchId);
		prepareWrite(search);
		dataRepository.deleteSavedSearch(search);
		return mapper.map(search, SavedSearchInfo.class);
	}


	@Override
	@Transactional(readOnly=true)
	public SavedSearchInfo[] getSavedSearches () {
		List<SavedSearchInfo> searches = new ArrayList<SavedSearchInfo>();
		for (SavedSearch search : dataRepository.getUserSavedSearches(currentUserId.get())) {
			searches.add(mapper.map(search, SavedSearchInfo.class));
		}
		return searches.toArray(new SavedSearchInfo[searches.size()]);
	}

	@Override
	@Transactional
	public SavedSearchInfo setSavedSearchName (long searchId, String name) {
		SavedSearch search = dataRepository.getSavedSearch(searchId);
		prepareWrite(search);
		search.setName(name);
		return mapper.map(search, SavedSearchInfo.class);
	}


	@Override
	@Transactional
	public SavedSearchDetails setSavedSearchDescription (long searchId,
			String description) {
		SavedSearch search = dataRepository.getSavedSearch(searchId);
		prepareWrite(search);
		search.setDescription(description);
		return mapper.map(search, SavedSearchDetails.class);
	}


	@Override
	@Transactional
	public SavedSearchDetails setSavedSearchPublic (long searchId, boolean isPublic) {
		SavedSearch search = dataRepository.getSavedSearch(searchId);
		prepareWrite(search);
		search.setPublic(isPublic);
		return mapper.map(search, SavedSearchDetails.class);
	}

}
