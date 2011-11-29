package org.gwtcom.client.view.profile.friends;

import org.gwtcom.shared.FriendEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;

public class FriendItem extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, FriendItem> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Label name;

	@UiField
	Label surname;

	public FriendItem() {
		this(new FriendEntryRemote());
	}

	public FriendItem(FriendEntryRemote entry) {
		initWidget(binder.createAndBindUi(this));

		setName(entry != null && entry.getFirstname() != null ? entry.getFirstname() : "");
		setSurname(entry != null && entry.getLastname() != null ? entry.getLastname() : "");
	}

	public Label getName() {
		return name;
	}

	public void setName(String text) {
		name.setText(text);
	}

	public Label getSurname() {
		return surname;
	}

	public void setSurname(String text) {
		surname.setText(text);
	}
}