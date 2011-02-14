package org.gwtcom.client.view.widget;

import org.gwtcom.client.view.widget.richTextToolbar.RichTextToolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class RichTextWidget extends ResizeComposite {

	interface Binder extends UiBinder<Widget, RichTextWidget> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided = true)
	RichTextToolbar toolbar;
	@UiField(provided = true)
	RichTextArea textarea;

	public RichTextWidget() {
		// Create the text area and toolbar
		textarea = new RichTextArea();
		textarea.ensureDebugId("cwRichText-area");
		toolbar = new RichTextToolbar(textarea);
		toolbar.ensureDebugId("cwRichText-toolbar");

		initWidget(binder.createAndBindUi(this));
	}

	public void setEnabled(Boolean enabled){
		textarea.setEnabled(enabled);
	}
	
	public String getContent() {
		return textarea.getText();
	}

	public void setContent(String content) {
		textarea.setText(content);
	}

	public String getContentasHTML() {
		return textarea.getHTML();
	}

	public void setContentAsHTML(String content) {
		SafeHtmlBuilder sh = new SafeHtmlBuilder();
		sh.appendEscaped(content);
		textarea.setHTML(sh.toSafeHtml().asString());
	}
}
