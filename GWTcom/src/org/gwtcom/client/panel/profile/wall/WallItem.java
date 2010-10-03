package org.gwtcom.client.panel.profile.wall;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class WallItem extends ResizeComposite {

	interface Binder extends UiBinder<DockLayoutPanel, WallItem> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	public WallItem() {
		initWidget(binder.createAndBindUi(this));
	}

}