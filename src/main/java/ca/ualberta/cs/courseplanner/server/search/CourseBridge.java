package ca.ualberta.cs.courseplanner.server.search;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import ca.ualberta.cs.courseplanner.entities.Course;


public class CourseBridge implements FieldBridge {

	@Override
	public void set (String field, Object object, Document document, LuceneOptions lucene) {
		Course course = (Course) object;
		StringBuilder value = new StringBuilder();
		value.append(course.getSubject())
			 .append(' ')
			 .append(course.getNumber())
			 .append(' ')
			 .append(course.getName())
			 .append('\n')
			 .append(course.getOrg1())
			 .append('\n')
			 .append(course.getOrg2())
			 .append('\n')
			 .append(course.getDescription());
		lucene.addFieldToDocument(field, value.toString(), document);
	}

}
