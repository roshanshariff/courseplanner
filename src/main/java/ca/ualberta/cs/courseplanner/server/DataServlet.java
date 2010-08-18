package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.client.DataService;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;
import ca.ualberta.cs.courseplanner.dto.PlanInfo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServlet extends RemoteServiceServlet implements DataService {
	
	private static final long serialVersionUID = -3955349790234109774L;

	private DataService service;
	
	public void setService (DataService service) {
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
