package ca.ualberta.cs.courseplanner.server;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import ca.ualberta.cs.courseplanner.client.DataService;
import ca.ualberta.cs.courseplanner.domain.Plan;
import ca.ualberta.cs.courseplanner.domain.User;
import ca.ualberta.cs.courseplanner.dto.*;


public class DataServiceImpl implements DataService {

	private DataRepository data;
	
	private Mapper mapper;
	
	public void setData (DataRepository data) {
		this.data = data;
	}
	
	public void setMapper (Mapper mapper) {
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly=true)
	public CourseDetails getCourseDetails (Long id) {
		return mapper.map(data.getCourse(id), CourseDetails.class);
	}

	@Override
	@Transactional
	public PlanInfo createPlan(String userId, String planName) {
		User user = data.getUser(userId);
		return mapper.map(data.createPlan(user, planName), PlanInfo.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PlanInfo> getPlans(String userId) {
		List<PlanInfo> plans = new ArrayList<PlanInfo>();
		for (Plan plan : data.getUser(userId).getPlans()) {
			plans.add(mapper.map(plan, PlanInfo.class));
		}
		return plans;
	}

}
