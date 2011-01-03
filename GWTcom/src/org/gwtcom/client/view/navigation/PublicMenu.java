package org.gwtcom.client.view.navigation;

import org.gwtcom.client.place.DateListPlace;
import org.gwtcom.client.place.NewsListPlace;
import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A component that displays a list of contacts.
 */
public class PublicMenu extends AbstractStackPanelInlay {

	public static interface CwConstants extends Constants {
	    String cwNewsTitle();

	    String cwDatesTitle();
	  }
	
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

	private final Anchor _news;

	private final Anchor _dates;

	private final PlaceController _placeController;

	public PublicMenu(PlaceController placeController, CwConstants constants) {
		_placeController = placeController;
		
		initWidget(binder.createAndBindUi(this));

		_news = addItem(constants.cwNewsTitle());
		_news.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>> PublicMenu.News.OnClick()");
				_placeController.goTo(new NewsListPlace());
			}
		});

		_dates = addItem(constants.cwDatesTitle());
		_dates.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(">>> PublicMenu.Termine.OnClick()");
				_placeController.goTo(new DateListPlace());
			}
		});

	}

	private Anchor addItem(final String item) {
		Anchor link = new Anchor(item);
		link.setStyleName(style.item());
		panel.add(link);
		return link;
	}

	@Override
	public void setLoggedIn(UserLoginRemote result) {
		// TODO Auto-generated method stub
		
	}
}
