package org.gwtcom.client.view.profile.wall;

import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WallPanel extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, WallPanel> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	VerticalPanel wallitems;
	
	public WallPanel() {
		initWidget(binder.createAndBindUi(this));
	}

	public void addWallItem(WallEntryRemote entry){
		System.out.println(">>>>> WallItem.addWallItem(WallEntryRemote)");
		WallItem item = new WallItem();
		wallitems.insert(item, 0);
	}
}