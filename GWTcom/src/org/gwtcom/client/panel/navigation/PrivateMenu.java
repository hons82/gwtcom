package org.gwtcom.client.panel.navigation;

import org.gwtcom.client.event.ProfileShowEvent;
import org.gwtcom.client.event.bus.EventBus;
import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
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

	private Label _noAccess;

	private Anchor _profile;

	private EventBus _eventbus;

	private HandlerRegistration _profileHandlerRegistration;

	public PrivateMenu(EventBus eventbus) {
		_eventbus = eventbus;

		initWidget(binder.createAndBindUi(this));

		initView();

	}

	/**
	 * 
	 */
	private void initView() {
		_noAccess = new Label("You need to be logged in to use this menu");
		panel.add(_noAccess);

		_profile = addItem("Profile");
		
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

			_profile.setVisible(true);
			_profileHandlerRegistration = _profile.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					System.out.println(">>> PrivateMenu.Profile.OnClick()");
					_eventbus.fireEvent(new ProfileShowEvent(loggedIn));
				}
			});
		} else {
			_noAccess.setVisible(true);

			_profile.setVisible(false);
			if (_profileHandlerRegistration!=null)
				_profileHandlerRegistration.removeHandler();
		}

	}
}
