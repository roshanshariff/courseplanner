package ca.ualberta.cs.courseplanner.client;

import java.util.List;

import ca.ualberta.cs.courseplanner.domain.Course;
import ca.ualberta.cs.courseplanner.dto.CourseDetails;
import ca.ualberta.cs.courseplanner.dto.PlanInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface DataServiceAsync {

	void getCourseDetails (Long id, AsyncCallback<CourseDetails> callback);

	void createPlan(String userId, String planName,
			AsyncCallback<PlanInfo> callback);

	void getPlans(String userId, AsyncCallback<List<PlanInfo>> callback);

}
