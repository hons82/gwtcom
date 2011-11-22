package org.gwtcom.client.view.navigation;

import java.util.ArrayList;
import java.util.List;

import org.gwtcom.client.place.PeopleViewPlace;
import org.gwtcom.client.place.ProfileChangePlace;
import org.gwtcom.client.place.ProfileViewPlace;
import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Social Menu
 */
public class SocialMenu extends AbstractStackPanelInlay {

	public static interface CwConstants extends Constants {
		String cwProfileTitle();

		String cwProfileChangeTitle();

		String cwPeopleTitle();

		String cwNoAccess();
	}

	interface Binder extends UiBinder<Widget, SocialMenu> {
	}

	interface Style extends CssResource {
		String item();
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	ComplexPanel panel;
	@UiField
	Style style;

	private final Label _noAccess;

	private final Anchor _profile;

	private final Anchor _profileChange;

	private final Anchor _people;

	private final List<HandlerRegistration> _socialHandlerRegistration;

	private final PlaceController _placeController;

	private final CwConstants _constants;

	public SocialMenu(PlaceController placeController, CwConstants constants) {
		_placeController = placeController;
		_constants = constants;

		initWidget(binder.createAndBindUi(this));

		_socialHandlerRegistration = new ArrayList<HandlerRegistration>();

		_noAccess = new Label(_constants.cwNoAccess());
		panel.add(_noAccess);

		_profile = addItem(_constants.cwProfileTitle());
		_profileChange = addItem(_constants.cwProfileChangeTitle());
		_people = addItem(_constants.cwPeopleTitle());
	}

	private Anchor addItem(final String item) {
		Anchor link = new Anchor(item);
		link.setStyleName(style.item());
		panel.add(link);
		return link;
	}

	@Override
	public void setLoggedIn(final UserLoginRemote loggedIn) {
		if (loggedIn != null) {
			_noAccess.setVisible(false);

			// Show your Profile
			_profile.setVisible(true);
			_socialHandlerRegistration.add(_profile.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					System.out.println(">>> PeopleMenu.Profile.OnClick()");
					_placeController.goTo(new ProfileViewPlace(loggedIn));
				}
			}));

			// Change the actual Profile
			_profileChange.setVisible(true);
			_socialHandlerRegistration.add(_profileChange.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					System.out.println(">>> PeopleMenu.ProfileChange.OnClick()");
					_placeController.goTo(new ProfileChangePlace(loggedIn));
				}
			}));

			// Change the actual Profile
			_people.setVisible(true);
			_socialHandlerRegistration.add(_people.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					System.out.println(">>> PeopleMenu.People.OnClick()");
					_placeController.goTo(new PeopleViewPlace(loggedIn));
				}
			}));
		} else {
			_noAccess.setVisible(true);

			_profile.setVisible(false);
			_profileChange.setVisible(false);
			_people.setVisible(false);

			removeAllPrivateHandler();
		}

	}

	private void removeAllPrivateHandler() {
		if (_socialHandlerRegistration != null)
			for (HandlerRegistration reg : _socialHandlerRegistration) {
				reg.removeHandler();
			}
	}
}
