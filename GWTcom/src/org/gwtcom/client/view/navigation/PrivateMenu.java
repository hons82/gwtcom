package org.gwtcom.client.view.navigation;

import java.util.ArrayList;
import java.util.List;

import org.gwtcom.client.place.ProfileChangeViewPlace;
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
 * A tree displaying a set of email folders.
 */
public class PrivateMenu extends AbstractStackPanelInlay {

	public static interface CwConstants extends Constants {
		String cwProfileTitle();

		String cwProfileChangeTitle();

		String cwNoAccess();
	}

	interface Binder extends UiBinder<Widget, PrivateMenu> {
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

	private final List<HandlerRegistration> _profileHandlerRegistration;

	private final PlaceController _placeController;

	private final CwConstants _constants;

	public PrivateMenu(PlaceController placeController, CwConstants constants) {
		_placeController = placeController;
		_constants = constants;

		initWidget(binder.createAndBindUi(this));

		_profileHandlerRegistration = new ArrayList<HandlerRegistration>();

		_noAccess = new Label(_constants.cwNoAccess());
		panel.add(_noAccess);

		_profile = addItem(_constants.cwProfileTitle());
		_profileChange = addItem(_constants.cwProfileChangeTitle());
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
			_profileHandlerRegistration.add(_profile.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					System.out.println(">>> PrivateMenu.Profile.OnClick()");
					_placeController.goTo(new ProfileViewPlace(loggedIn));
				}
			}));

			// Change the actual Profile
			_profileChange.setVisible(true);
			_profileHandlerRegistration.add(_profileChange.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					System.out.println(">>> PrivateMenu.ProfileChange.OnClick()");
					_placeController.goTo(new ProfileChangeViewPlace(loggedIn));
				}
			}));
		} else {
			_noAccess.setVisible(true);

			_profile.setVisible(false);
			_profileChange.setVisible(false);

			removeAllPrivateHandler();
		}

	}

	private void removeAllPrivateHandler() {
		if (_profileHandlerRegistration != null)
			for (HandlerRegistration reg : _profileHandlerRegistration) {
				reg.removeHandler();
			}
	}
}
