package ca.ualberta.cs.courseplanner.client.views.cell;

import ca.ualberta.cs.courseplanner.model.PlanInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;


public class PlanHyperlinkRenderer extends AbstractSafeHtmlRenderer<PlanInfo> {

	public interface Templates extends SafeHtmlTemplates {
		@Template("<a href=\"#plan:{0}\">{1}</a>")
		SafeHtml planHyperlink (long id, String name);
	}

	private static final Templates TEMPLATES = GWT.create(Templates.class);

	@Override
	public SafeHtml render (PlanInfo plan) {
		return TEMPLATES.planHyperlink(
				plan.getId(), plan.getName()
		);
	}

}
