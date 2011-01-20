package org.gwtcom.client.view.profile.wall;

import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WallPanel extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, WallPanel> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TextBox inputContent;
	@UiField
	Button inputSubmit;
	@UiField
	VerticalPanel wallitems;

	public WallPanel() {
		initWidget(binder.createAndBindUi(this));
	}

	public void addWallItem(WallEntryRemote entry) {
		System.out.println(">>>>> WallItem.addWallItem(WallEntryRemote)");
		WallItem item = new WallItem(entry);
		wallitems.insert(item, 0);
	}

	public void clearWall() {
		wallitems.clear();
	}

	public String getInputContent() {
		return inputContent.getValue();
	}

	public void addWallPostClickHandler(ClickHandler clickHandler) {
		inputSubmit.addClickHandler(clickHandler);
	}

}