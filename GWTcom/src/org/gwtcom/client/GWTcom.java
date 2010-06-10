package org.gwtcom.client;

import org.gwtcom.client.panel.TopPanel;
import org.gwtcom.client.service.AuthenticationService;
import org.gwtcom.client.service.DocumentService;
import org.gwtcom.client.service.GreetingService;
import org.gwtcom.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTcom implements EntryPoint {
	
	interface Binder extends UiBinder<DockLayoutPanel, GWTcom> { }
	private static final Binder binder = GWT.create(Binder.class);

	@UiField TopPanel topPanel;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Inject global styles.
	    //GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();

	    // Create the UI defined in Mail.ui.xml.
	    DockLayoutPanel outer = binder.createAndBindUi(this);

	    // Get rid of scrollbars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area.
	    Window.enableScrolling(false);
	    Window.setMargin("0px");

	    // Special-case stuff to make topPanel overhang a bit.
	    Element topElem = outer.getWidgetContainerElement(topPanel);
	    topElem.getStyle().setZIndex(2);
	    topElem.getStyle().setOverflow(Overflow.VISIBLE);
		
	    RootLayoutPanel root = RootLayoutPanel.get();
	    root.add(outer);
	    
		final Button sendButton = new Button("Send");
		final Button publicButton = new Button("Get Public");
		final Button privateButton = new Button("Get Private");
		final Button loginButton = new Button("Login");
		final Button logoutButton = new Button("Logout");

		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// additional buttons
		RootPanel.get("moreButtonsContainer").add(publicButton);
		RootPanel.get("moreButtonsContainer").add(privateButton);
		RootPanel.get("moreButtonsContainer").add(loginButton);
		RootPanel.get("moreButtonsContainer").add(logoutButton);
		
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				GreetingService.Util.getInstance().greetServer(textToServer, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);

		// wire additional buttons
		publicButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DocumentService.Util.getInstance().getNumberOfPublicPublications(new AsyncCallback<Integer>() {
					public void onSuccess(Integer result) {
						Window.alert("result: " + result);
					}

					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
			}
		});

		privateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DocumentService.Util.getInstance().getNumberOfPrivatePublications(new AsyncCallback<Integer>() {
					public void onSuccess(Integer result) {
						Window.alert("result: " + result);
					}

					public void onFailure(Throwable caught) {
						Window.alert("maybe you have to login to access this method, you moron!");
					}
				});
			}
		});

		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AuthenticationService.Util.getInstance().authenticate("daniel", "daniel", new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						Window.alert("Logged in.");
					}

					public void onFailure(Throwable caught) {
						Window.alert("Error during login.");
					}
				});
			}
		});

		logoutButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AuthenticationService.Util.getInstance().logout(new AsyncCallback<Void>() {
					public void onSuccess(Void result) {
						Window.alert("Logged out.");
					}

					public void onFailure(Throwable caught) {
						Window.alert("Error during logout.");
					}
				});
			}
		});
	}
}