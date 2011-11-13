package org.gwtcom.client.view.profile.friends;

import org.gwtcom.shared.FriendEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextBox;

public class FriendsPanel extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, FriendsPanel> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TextBox inputContent;
	@UiField
	Button inputSubmit;
	@UiField
	HorizontalPanel friendItems;

	public FriendsPanel() {
		initWidget(binder.createAndBindUi(this));
	}

	public void addFriendItem(FriendEntryRemote entry) {
		System.out.println(">>>>> FriendItem.addFriendItem(FriendEntryRemote)");
		FriendItem item = new FriendItem(entry);
		friendItems.insert(item, 0);
	}

	public void clearFriends() {
		friendItems.clear();
	}

	public String getInputContent() {
		return inputContent.getValue();
	}

	public void addFriendsClickHandler(ClickHandler clickHandler) {
		inputSubmit.addClickHandler(clickHandler);
	}

}