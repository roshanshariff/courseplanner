package ca.ualberta.cs.courseplanner.server.search;

import org.hibernate.transform.BasicTransformerAdapter;

import ca.ualberta.cs.courseplanner.model.CourseInfo;


public class CourseInfoResultTransformer extends BasicTransformerAdapter {

	private static final long serialVersionUID = 8805368903546013184L;
	
	@Override
	public Object transformTuple (Object[] tuple, String[] aliases) {
		CourseInfo result = new CourseInfo();
		for (int i = 0; i < tuple.length; ++i) {
			if (aliases[i].equals("id")) result.setId((Long)tuple[i]);
			else if (aliases[i].equals("subject.id")) result.setSubjectId((String)tuple[i]);
			else if (aliases[i].equals("number")) result.setNumber((String)tuple[i]);
			else if (aliases[i].equals("name")) result.setName((String)tuple[i]);
		}
		return result;
	}

}
