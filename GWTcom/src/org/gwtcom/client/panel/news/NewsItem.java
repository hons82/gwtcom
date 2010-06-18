package org.gwtcom.client.panel.news;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a news message.
 */
public class NewsItem extends ResizeComposite {

	interface Binder extends UiBinder<Widget, NewsItem> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Hidden id;
	@UiField
	Element title;
	@UiField
	Element author;
	@UiField
	Element date;
	@UiField
	HTML content;

	public NewsItem() {
		initWidget(binder.createAndBindUi(this));
	}
	
	public NewsItem(NewsItemRemote item){
		this();
		setItem(item);
	}

	public void setItem(NewsItemRemote item) {
		id.setValue(String.valueOf(item.getId()));
		title.setInnerText(item.getTitle());
		author.setInnerText(item.getAuthor());
		date.setInnerHTML(item.getDateAdded().toString());

		// WARNING: For the purposes of this demo, we're using HTML directly, on
		// the assumption that the "server" would have appropriately scrubbed
		// the
		// HTML. Failure to do so would open your application to XSS attacks.
		content.setHTML("blabla");
	}
	
	public Long getId(){
		return Long.valueOf(id.getValue());
	}
}