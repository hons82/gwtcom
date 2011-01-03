package org.gwtcom.client.view;

import org.gwtcom.client.event.ILoginLogoutClickEvent;
import org.gwtcom.client.event.LoginLogoutClickEvent;
import org.gwtcom.client.i18n.GWTcomConstants;
import org.gwtcom.client.service.AuthenticationService;
import org.gwtcom.client.service.AuthenticationServiceAsync;
import org.gwtcom.client.view.navigation.Shortcuts;
import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class GWTmainView extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, GWTmainView> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TopPanel topPanel;
	@UiField(provided=true)
	Shortcuts shortcuts;
	@UiField
	SimplePanel container;

	private final DockLayoutPanel _outer;

	private final EventBus _eventbus;

	private final PlaceController _placeController;

	private final GWTcomConstants _constants;

	public GWTmainView(EventBus eventbus, PlaceController placeController, GWTcomConstants constants) {
		_eventbus = eventbus;
		_placeController = placeController;
		_constants = constants;
		// Inject global styles.
		// GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();

		shortcuts = new Shortcuts(_placeController, _constants);
		
		// Create the UI defined in GWTcom.ui.xml.
		_outer = binder.createAndBindUi(this);

		// Special-case stuff to make topPanel overhang a bit.
		Element topElem = _outer.getWidgetContainerElement(topPanel);
		topElem.getStyle().setZIndex(2);
		topElem.getStyle().setOverflow(Overflow.VISIBLE);

		initView();

		bind();
	}

	private void bind() {
		_eventbus.addHandler(LoginLogoutClickEvent.TYPE, new ILoginLogoutClickEvent() {

			@Override
			public void onLoginLogoutClick(LoginLogoutClickEvent event) {
				topPanel.setLoggedIn(event.isLoggedIn());
				shortcuts.setLoggedIn(event.isLoggedIn());
			}
		});
	}

	/**
	 * 
	 */
	private void initView() {
		AuthenticationServiceAsync service = GWT.create(AuthenticationService.class);
		service.isLoggedIn(new AsyncCallback<UserLoginRemote>() {

			@Override
			public void onFailure(Throwable caught) {
				topPanel.setLoggedIn(null);
				shortcuts.setLoggedIn(null);
			}

			@Override
			public void onSuccess(UserLoginRemote result) {
				topPanel.setLoggedIn(result);
				shortcuts.setLoggedIn(result);
			}
		});

		topPanel.addLoginClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final LoginDialog dlg = new LoginDialog();

				dlg.addLoginClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						dlg.setLoading(true);

						AuthenticationServiceAsync service = GWT.create(AuthenticationService.class);
						service.authenticate(dlg.getUserText(), dlg.getPassText(), new AsyncCallback<UserLoginRemote>() {

							@Override
							public void onSuccess(UserLoginRemote result) {
								_eventbus.fireEvent(new LoginLogoutClickEvent(result));
								dlg.hide();
							}

							@Override
							public void onFailure(Throwable caught) {
								dlg.setLoading(false);
								Window.alert("Authentication failed");
								_eventbus.fireEvent(new LoginLogoutClickEvent(null));
							}
						});
					}
				});

				dlg.show();
				dlg.center();

			}
		});

		topPanel.addLogoutClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuthenticationServiceAsync service = GWT.create(AuthenticationService.class);
				service.logout(new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO: boh???
						Window.alert("Logout failed");
					}

					@Override
					public void onSuccess(Void result) {
						_eventbus.fireEvent(new LoginLogoutClickEvent(null));
					}
				});

			}
		});

		topPanel.addLogoutClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuthenticationServiceAsync service = GWT.create(AuthenticationService.class);
				service.logout(new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO: boh???
						Window.alert("Logout failed");
					}

					@Override
					public void onSuccess(Void result) {
						_eventbus.fireEvent(new LoginLogoutClickEvent(null));
					}
				});

			}
		});
	}

	@Override
	public Widget asWidget() {
		return _outer;
	}

	public AcceptsOneWidget getDetailContainer() {
		return container;
	}

}
