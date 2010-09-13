package org.gwtcom.client.panel;

import org.gwtcom.client.event.DateListShowEvent;
import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.event.NewsListShowEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * A component that displays a list of contacts.
 */
public class PublicMenu extends Composite {

	interface Binder extends UiBinder<Widget, PublicMenu> {
	}

	interface Style extends CssResource {
		String item();
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	ComplexPanel panel;
	@UiField
	Style style;

	private Anchor _news;

	private Anchor _dates;

	private EventBus _eventbus;

	public PublicMenu(EventBus eventbus) {
		_eventbus = eventbus;
		
		initWidget(binder.createAndBindUi(this));

		_news = addItem("News");
		_news.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>> PublicMenu.News.OnClick()");
				_eventbus.fireEvent(new NewsListShowEvent());
			}
		});

		_dates = addItem("Termine");
		_dates.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>> PublicMenu.Termine.OnClick()");
				_eventbus.fireEvent(new DateListShowEvent());
			}
		});

	}

	private Anchor addItem(final String item) {
		Anchor link = new Anchor(item);
		link.setStyleName(style.item());
		panel.add(link);
		return link;
	}
}
