package ca.ualberta.cs.courseplanner.server.search;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import ca.ualberta.cs.courseplanner.entities.Course;


public class CourseOrgIdBridge implements FieldBridge {

	@Override
	public void set (String field, Object object, Document document, LuceneOptions lucene) {
		Course course = (Course) object;
		lucene.addFieldToDocument(field, course.getOrg1().getId(), document);
		lucene.addFieldToDocument(field, course.getOrg2().getId(), document);
	}

}
