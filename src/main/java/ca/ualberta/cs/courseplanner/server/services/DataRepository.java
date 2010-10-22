package ca.ualberta.cs.courseplanner.server.services;

import java.util.List;

import ca.ualberta.cs.courseplanner.entities.*;
import ca.ualberta.cs.courseplanner.model.Search;

public interface DataRepository {

	
	public Course getCourse (Long id);
	

	public User getUser (String id);
	

	public Plan createPlan (User user, String name);
	
	public Plan getPlan (Long id);
	
	public List<Plan> getUserPlans (String userId);
	
	public void deletePlan (Plan plan);
	
	
	public SavedSearch createSavedSearch (User user, String name, Search search);
	
	public SavedSearch getSavedSearch (Long id);
	
	public List<SavedSearch> getUserSavedSearches (String userId);
	
	public void deleteSavedSearch (SavedSearch search);
	
	
	public List<Subject> getSubjects ();
	
	public List<Organisation> getOrganisations ();
	
}
