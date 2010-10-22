package ca.ualberta.cs.courseplanner.client.views.cell;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;


public class SafeHtmlRendererCell<T> extends AbstractSafeHtmlCell<T> {
	
	public SafeHtmlRendererCell (SafeHtmlRenderer<T> renderer) {
		super(renderer);
	}

	@Override
	protected void render (SafeHtml html, Object object, SafeHtmlBuilder builder) {
		if (html != null) {
			builder.append(html);
		}
	}

}
