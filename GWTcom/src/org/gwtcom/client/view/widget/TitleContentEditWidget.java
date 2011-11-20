package org.gwtcom.client.view.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TitleContentEditWidget extends ResizeComposite {

	interface Binder extends UiBinder<Widget, TitleContentEditWidget> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TextBox title;

	@UiField
	RichTextWidget content;

	public TitleContentEditWidget() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public String getTitle() {
		return title.getText();
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		this.title.setText(title);
	}

	public void setEnabled(boolean enabled) {
		title.setEnabled(enabled);
		content.setEnabled(enabled);
	}

	public void setContentAsHTML(String content) {
		this.content.setContentAsHTML(content);
	}

	public void setContent(String content) {
		this.content.setContent(content);
	}

	public String getContentasHTML() {
		return content.getContentasHTML();
	}

	public String getContent() {
		return content.getContent();
	}

}
