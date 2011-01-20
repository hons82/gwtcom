package org.gwtcom.client.view;

import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
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
	/**
	 * A drop box used to change the locale.
	 */
	@UiField
	ListBox localeBox;

	public TopPanel() {
		initWidget(binder.createAndBindUi(this));
		signOutLink.setVisible(false);

		initializeLocaleBox();

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
		if (result != null) {
			greet.setText("Welcome back, " + result.getName());
			signInLink.setVisible(false);
			signOutLink.setVisible(true);
		} else {
			greet.setText("Welcome!");
			signInLink.setVisible(true);
			signOutLink.setVisible(false);
		}
	}

	/**
	 * Initialize the {@link ListBox} used for locale selection.
	 */
	private void initializeLocaleBox() {
		String currentLocale = LocaleInfo.getCurrentLocale().getLocaleName();
		if (currentLocale.equals("default")) {
			currentLocale = "en";
		}
		String[] localeNames = LocaleInfo.getAvailableLocaleNames();
		for (String localeName : localeNames) {
			if (!localeName.equals("default")) {
				String nativeName = LocaleInfo.getLocaleNativeDisplayName(localeName);
				localeBox.addItem(nativeName, localeName);
				if (localeName.equals(currentLocale)) {
					localeBox.setSelectedIndex(localeBox.getItemCount() - 1);
				}
			}
		}
		localeBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String localeName = localeBox.getValue(localeBox.getSelectedIndex());
				UrlBuilder builder = Location.createUrlBuilder().setParameter("locale", localeName);
				Window.Location.replace(builder.buildString());
			}
		});
	}
}
