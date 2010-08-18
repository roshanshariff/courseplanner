package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.domain.*;

public interface DataRepository {
	
	public Course getCourse (Long id);
	
	public User getUser (String id);
	
	public Plan createPlan (User user, String name);
	
	public Plan getPlan (Long id);
	
	public List<Subject> getSubjects ();
	
	public List<Organisation> getOrganisations ();
	
}
