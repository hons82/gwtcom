package org.gwtcom.client.panel;

import org.gwtcom.client.event.EventBus;
import org.gwtcom.client.event.ILoginLogoutClickEvent;
import org.gwtcom.client.event.LoginLogoutClickEvent;
import org.gwtcom.client.service.AuthenticationService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class GWTmainView extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, GWTmainView> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TopPanel topPanel;
	@UiField
	Shortcuts shortcuts;
	@UiField
	LayoutPanel container;

	private DockLayoutPanel _outer;

	private EventBus _eventbus;

	public GWTmainView(EventBus eventbus) {
		_eventbus = eventbus;
		// Inject global styles.
		// GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();

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
				topPanel.setLogedIn(event.isLoggedIn());
			}
		});
	}

	/**
	 * 
	 */
	private void initView() {
		AuthenticationService.Util.getInstance().isLoggedIn(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				topPanel.setLogedIn(false);
			}

			@Override
			public void onSuccess(Boolean result) {
				topPanel.setLogedIn(result);
			}
		});
		
		
		topPanel.addLoginClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuthenticationService.Util.getInstance().authenticate("hons", "password", new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						_eventbus.fireEvent(new LoginLogoutClickEvent(result));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Authentication failed");
						_eventbus.fireEvent(new LoginLogoutClickEvent(false));
					}
				});
				
			}
		});
		
		topPanel.addLogoutClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuthenticationService.Util.getInstance().logout(new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						//TODO: boh???
						Window.alert("Logout failed");
					}

					@Override
					public void onSuccess(Void result) {
						_eventbus.fireEvent(new LoginLogoutClickEvent(false));
					}
				});
				
			}
		});
	}

	public HasWidgets asWidget() {
		return _outer;
	}

	public LayoutPanel getDetailContainer() {
		return container;
	}

}
