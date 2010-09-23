package org.gwtcom.client.panel;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * The top panel, which contains the 'welcome' message and various links.
 */
public class TopPanel extends Composite {

	interface Binder extends UiBinder<Widget, TopPanel> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Label greet;
	@UiField
	Anchor signInLink;
	@UiField
	Anchor signOutLink;
	@UiField
	Anchor aboutLink;

	public TopPanel() {
		initWidget(binder.createAndBindUi(this));
		signOutLink.setVisible(false);

	}

	@UiHandler("aboutLink")
	void onAboutClicked(ClickEvent event) {
		// When the 'About' item is selected, show the AboutDialog.
		// Note that showing a dialog box does not block -- execution continues
		// normally, and the dialog fires an event when it is closed.
		// AboutDialog dlg = new AboutDialog();
		// dlg.show();
		// dlg.center();
	}

	void addLoginClickHandler(ClickHandler handler) {
		signInLink.addClickHandler(handler);
	}

	void addLogoutClickHandler(ClickHandler handler) {
		signOutLink.addClickHandler(handler);
	}

	@Override
	protected void onLoad() {
		System.out.println(">>>>> TopPanel.onLoad");
	};

	public void setLoggedIn(UserLoginRemote result) {
		if (result!=null) {
			greet.setText("Welcome back, "+result.getName());
			signInLink.setVisible(false);
			signOutLink.setVisible(true);
		} else {
			greet.setText("Welcome!");
			signInLink.setVisible(true);
			signOutLink.setVisible(false);
		}
	}
}
