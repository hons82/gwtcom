package org.gwtcom.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
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
 * A simple example of an 'about' dialog box.
 */
public class LoginDialog extends DialogBox {

	interface Binder extends UiBinder<Widget, LoginDialog> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TextBox user;
	@UiField
	PasswordTextBox pass;
	@UiField
	Button cancelButton;
	@UiField
	Button loginButton;
	@UiField
	Label loading;

	public LoginDialog() {
		// Use this opportunity to set the dialog's caption.
		setText("Login");
		setWidget(binder.createAndBindUi(this));

		// Let's disallow non-numeric entry in the normal text box.
		pass.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (!((event.getNativeKeyCode() != KeyCodes.KEY_TAB) && (event.getNativeKeyCode() != KeyCodes.KEY_BACKSPACE)
						&& (event.getNativeKeyCode() != KeyCodes.KEY_DELETE) && (event.getNativeKeyCode() != KeyCodes.KEY_ENTER)
						&& (event.getNativeKeyCode() != KeyCodes.KEY_HOME) && (event.getNativeKeyCode() != KeyCodes.KEY_END)
						&& (event.getNativeKeyCode() != KeyCodes.KEY_LEFT) && (event.getNativeKeyCode() != KeyCodes.KEY_UP)
						&& (event.getNativeKeyCode() != KeyCodes.KEY_RIGHT) && (event.getNativeKeyCode() != KeyCodes.KEY_DOWN))) {
					// TextBox.cancelKey() suppresses the current keyboard
					// event.
					((TextBox) event.getSource()).cancelKey();

				}
			}
		});

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
				loginButton.click();
				break;
			case KeyCodes.KEY_ESCAPE:
				hide();
				break;
			}
		}
	}

	@UiHandler("cancelButton")
	void onSignOutClicked(ClickEvent event) {
		hide();
	}

	public void addLoginClickHandler(ClickHandler handler) {
		loginButton.addClickHandler(handler);
	}

	public String getUserText() {
		return user.getText();
	}

	public String getPassText() {
		return pass.getText();
	}

	public void setLoading(boolean visible) {
		loading.setVisible(visible);
	}

	@Override
	public void show() {
		super.show();
		setLoading(false);
		user.setFocus(true);
	}
}
