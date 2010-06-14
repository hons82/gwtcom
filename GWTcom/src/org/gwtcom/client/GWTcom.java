package org.gwtcom.client;

import org.gwtcom.client.panel.NewsList;
import org.gwtcom.client.panel.Shortcuts;
import org.gwtcom.client.panel.TopPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTcom implements EntryPoint {
	
	interface Binder extends UiBinder<DockLayoutPanel, GWTcom> { }
	private static final Binder binder = GWT.create(Binder.class);

	@UiField TopPanel topPanel;
	@UiField Shortcuts shortcuts;
	@UiField LayoutPanel container;
	
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

	    // Create the UI defined in GWTcom.ui.xml.
	    DockLayoutPanel outer = binder.createAndBindUi(this);
	    
	    
	    // Get rid of scrollbars, and clear out the window's built-in margin,
	    // because we want to take advantage of the entire client area.
	    Window.enableScrolling(false);
	    Window.setMargin("0px");

	    // Special-case stuff to make topPanel overhang a bit.
	    Element topElem = outer.getWidgetContainerElement(topPanel);
	    topElem.getStyle().setZIndex(2);
	    topElem.getStyle().setOverflow(Overflow.VISIBLE);

	    container.add(new NewsList());
	    RootLayoutPanel root = RootLayoutPanel.get();
	    root.add(outer);
	}
}