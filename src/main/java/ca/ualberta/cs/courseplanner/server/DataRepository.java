package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.entities.*;

public interface DataRepository {
	
	public Course getCourse (Long id);
	
	public User getUser (String id);
	
	public Plan createPlan (User user, String name);
	
	public Plan getPlan (Long id);
	
	public List<Plan> getUserPlans (String userId);
	
	public void deletePlan (Plan plan);
	
	public List<Subject> getSubjects ();
	
	public List<Organisation> getOrganisations ();
	
}
