package org.gwtcom.client.view.news;

import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a news message.
 */
public class NewsItemImpl extends ResizeComposite implements NewsItem {

	interface Binder extends UiBinder<Widget, NewsItemImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Hidden id;
	@UiField
	PushButton back;
	@UiField
	Element title;
	@UiField
	Element author;
	@UiField
	Element date;
	@UiField
	RichTextArea content;

	private Presenter _presenter;

	public NewsItemImpl() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setData(NewsItemRemote item) {
		System.out.println(">>>>> NewsItem.setData()");
		if (item != null) {
			DateTimeFormat fmt = DateTimeFormat.getFormat("EEE. dd MMM. yyyy HH:mm:ss");

			id.setValue(String.valueOf(item.getId()));
			title.setInnerText(item.getTitle());
			author.setInnerText(item.getAuthor() != null
					&& (item.getAuthor().getLastname() != null || item.getAuthor().getFirstname() != null) ? (item.getAuthor()
					.getLastname() != null ? item.getAuthor().getLastname() : "")
					+ " "
					+ (item.getAuthor().getFirstname() != null ? item.getAuthor().getFirstname() : "") : "<anonymous>");
			date.setInnerHTML(fmt.format(item.getDateAdded()));
			// on DB safe it should be checked for HTML errors and so on, so here it can just be added
			content.setHTML(item.getContent() != null ? item.getContent() : "");
		} else {
			id.setValue(String.valueOf(-1));
			title.setInnerText("<empty>");
			author.setInnerText("<empty>");
			date.setInnerHTML("<empty>");

			content.setText("");
		}
	}

	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@UiHandler("back")
	public void onClickBack(ClickEvent e) {
		_presenter.goTo(new NewsListPlace());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}
}