package org.gwtcom.client.view.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class CompleteRichTextArea extends Composite {

	public CompleteRichTextArea() {
		initWidget(init());
	}

	private Widget init() {
		// Create the text area and toolbar
		RichTextArea area = new RichTextArea();
		area.ensureDebugId("cwRichText-area");
		// area.setSize("100%", "14em");
		// TODO:
		// http://code.google.com/p/google-web-toolkit/source/browse/trunk/samples/showcase/src/com/google/gwt/sample/showcase/client/content/text/RichTextToolbar.java?r=4142
		// RichTextToolbar toolbar = new RichTextToolbar(area);
		// toolbar.ensureDebugId("cwRichText-toolbar");
		// toolbar.setWidth("100%");

		// Add the components to a panel
		Grid grid = new Grid(2, 1);
		grid.setStyleName("cw-RichText");
		// grid.setWidget(0, 0, toolbar);
		grid.setWidget(1, 0, area);
		return grid;
	}

}
