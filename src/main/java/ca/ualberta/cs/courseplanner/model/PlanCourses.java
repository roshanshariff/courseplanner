package ca.ualberta.cs.courseplanner.model;

import java.util.Map;


public class PlanCourses extends PlanDetails {
	
	private static final long serialVersionUID = -714328196254635786L;

	
	private Map<CourseInfo, CoursePlanInfo> courses;
	
	
	public Map<CourseInfo, CoursePlanInfo> getCourses () { return courses; }
	
	public void setCourses (Map<CourseInfo, CoursePlanInfo> courses) { this.courses = courses; }

}
