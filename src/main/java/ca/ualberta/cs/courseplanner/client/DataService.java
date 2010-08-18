package ca.ualberta.cs.courseplanner.client;

import java.util.List;

import ca.ualberta.cs.courseplanner.dto.CourseDetails;
import ca.ualberta.cs.courseplanner.dto.PlanInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("coursedata")
public interface DataService extends RemoteService {
	
	public CourseDetails getCourseDetails (Long id);
	
	public PlanInfo createPlan (String userId, String planName);
	
	public List<PlanInfo> getPlans (String userId);
	
}
