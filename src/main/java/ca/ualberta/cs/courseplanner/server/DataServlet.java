package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.dto.CourseDetails;
import ca.ualberta.cs.courseplanner.dto.PlanInfo;
import ca.ualberta.cs.courseplanner.services.CourseDataService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServlet extends RemoteServiceServlet implements CourseDataService {
	
	private static final long serialVersionUID = -3955349790234109774L;

	private CourseDataService service;
	
	public void setService (CourseDataService service) {
		this.service = service;
	}

	@Override
	public CourseDetails getCourseDetails(Long id) {
		return service.getCourseDetails(id);
	}

	@Override
	public PlanInfo createPlan(String userId, String planName) {
		return service.createPlan(userId, planName);
	}

	@Override
	public List<PlanInfo> getPlans(String userId) {
		return service.getPlans(userId);
	}

}
