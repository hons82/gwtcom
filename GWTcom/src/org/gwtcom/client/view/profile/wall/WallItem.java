package org.gwtcom.client.view.profile.wall;

import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;

public class WallItem extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, WallItem> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	Label username;

	@UiField
	Label content;

	public WallItem() {
		this(new WallEntryRemote());
	}

	public WallItem(WallEntryRemote entry) {
		initWidget(binder.createAndBindUi(this));

		//TODO: can still be ""
		setUsername(entry.getAuthor() != null ? (entry.getAuthor().getName() != null ? entry.getAuthor().getName()
				: "") + " " + (entry.getAuthor().getSurname() != null ? entry.getAuthor().getSurname() : "") : "<anonymous>");
		setContent(entry.getContent());
	}

	public void setUsername(String value) {
		username.setText(value);
	}

	public void setContent(String value) {
		content.setText(value);
	}

}