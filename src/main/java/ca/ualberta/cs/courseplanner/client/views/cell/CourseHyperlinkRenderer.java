package ca.ualberta.cs.courseplanner.client.views.cell;

import ca.ualberta.cs.courseplanner.model.CourseInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;


public class CourseHyperlinkRenderer extends AbstractSafeHtmlRenderer<CourseInfo> {

	public interface Templates extends SafeHtmlTemplates {
		@Template("<a href=\"#course:{0}\" title=\"{3}\">{1} {2}</a>")
		SafeHtml courseHyperlink (long id, String subjectId, String number, String name);
	}

	private static final Templates TEMPLATES = GWT.create(Templates.class);

	@Override
	public SafeHtml render (CourseInfo course) {
		return TEMPLATES.courseHyperlink(
				course.getId(), course.getSubjectId(), course.getNumber(), course.getName()
		);
	}

}
