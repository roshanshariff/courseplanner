package ca.ualberta.cs.courseplanner.server;

import java.util.List;

import ca.ualberta.cs.courseplanner.domain.*;
import ca.ualberta.cs.courseplanner.dto.*;


public interface CourseData {
	
	public Course getCourse (Long id);
	
	public CourseDetails getCourseDetails (Long id);
	
	public List<Subject> getSubjects ();
	
}
