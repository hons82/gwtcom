package org.gwtcom.client.panel.dates;

import org.gwtcom.client.presenter.DateItemPresenter;
import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A composite for displaying the details of a news message.
 */
public class DateItem extends ResizeComposite implements DateItemPresenter.Display {

	interface Binder extends UiBinder<Widget, DateItem> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Hidden id;
	@UiField
	Button back;
	@UiField
	Element title;
	@UiField
	Element author;
	@UiField
	Element date;
	@UiField
	HTML content;

	public DateItem() {
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void setData(DateItemRemote item) {
		System.out.println(">>>>> NewsItem.setData()");
		if (item != null) {
			DateTimeFormat fmt = DateTimeFormat.getFormat("EEE. dd MMM. yyyy HH:mm:ss");
			
			id.setValue(String.valueOf(item.getId()));
			title.setInnerText(item.getTitle());
			author.setInnerText(item.getAuthor() != null ? item.getAuthor().getSurname() + " " + item.getAuthor().getName() : "<empty>");
			date.setInnerHTML(fmt.format(item.getDateAdded()));

			// WARNING: For the purposes of this demo, we're using HTML
			// directly, on
			// the assumption that the "server" would have appropriately
			// scrubbed
			// the
			// HTML. Failure to do so would open your application to XSS
			// attacks.
			content.setHTML("blabla");
		}else{
			id.setValue(String.valueOf(-1));
			title.setInnerText("<empty>");
			author.setInnerText("<empty>");
			date.setInnerHTML("<empty>");

			content.setHTML("");
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

	@Override
	public void startProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public Button getBackButton() {
		return back;
	}

}