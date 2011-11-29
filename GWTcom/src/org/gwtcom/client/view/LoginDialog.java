package org.gwtcom.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple example of a 'login' dialog box.
 */
public class LoginDialog extends DialogBox {

	interface Binder extends UiBinder<Widget, LoginDialog> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TextBox userName;

	@UiField
	PasswordTextBox password;

	@UiField
	Button cancelBtn;

	@UiField
	Button submitBtn;

	@UiField
	Label loading;

	public LoginDialog() {
		// setText("Login");
		setWidget(binder.createAndBindUi(this));

		setAnimationEnabled(true);
		setGlassEnabled(true);
	}

	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent preview) {
		super.onPreviewNativeEvent(preview);

		NativeEvent evt = preview.getNativeEvent();
		if (evt.getType().equals("keydown")) {
			// Use the popup's key preview hooks to close the dialog when either
			// enter or escape is pressed.
			switch (evt.getKeyCode()) {
			case KeyCodes.KEY_ENTER:
				submitBtn.click();
				break;
			case KeyCodes.KEY_ESCAPE:
				hide();
				break;
			}
		}
	}

	@UiHandler("cancelBtn")
	void onSignOutClicked(ClickEvent event) {
		hide();
	}

	public void addLoginClickHandler(ClickHandler handler) {
		submitBtn.addClickHandler(handler);
	}

	public String getUserText() {
		return userName.getText();
	}

	public String getPassText() {
		return password.getText();
	}

	public void setLoading(boolean visible) {
		loading.setVisible(visible);
	}

	@Override
	public void show() {
		super.show();
		setLoading(false);
		userName.setFocus(true);
	}
}
