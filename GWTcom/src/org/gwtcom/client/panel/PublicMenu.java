package org.gwtcom.client.panel;

import org.gwtcom.client.presenter.NewsListPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
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

	public PublicMenu() {
		initWidget(binder.createAndBindUi(this));

		_news = addItem("News");
		_news.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>> PublicMenu.News.OnClick()");
				History.newItem(NewsListPresenter.PLACE.getId());
			}
		});

		_dates = addItem("Termine");
		_dates.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>> PublicMenu.Termine.OnClick()");
			//	History.newItem(DatesList.PLACE.getId());
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
