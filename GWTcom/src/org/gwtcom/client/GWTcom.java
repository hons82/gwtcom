package org.gwtcom.client;

import org.gwtcom.client.gin.GWTcomGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTcom implements EntryPoint {
	
	private final GWTcomGinjector injector = GWT.create(GWTcomGinjector.class);

	interface GlobalResources extends ClientBundle {
	    @NotStrict
	    @Source("GWTcom.css")
	    CssResource css();
	  }
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Inject global styles.
	    GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
		
		// Get rid of scrollbars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area.
		Window.enableScrolling(false);
		Window.setMargin("0px");
		
		injector.getPlaceManager();
		
	//If a presenter is providing a Place, I discovered that the presenter has to be
	//instantiated at the start of the application or else the Presenters will will not handle PlaceRequestEvent properly and history mechanism properly
	//This is the current behavior of  gwt-presenter release 1.0. I hope future version will provide lazy-loading of Presenters
	//
	/**/	injector.getNewsListPresenter(); /**/
	/**/	injector.getNewsItemPresenter(); /**/
	/**/	injector.getDateListPresenter(); /**/
	/**/	injector.getDateItemPresenter(); /**/
	/**/	injector.getProfileViewPresenter(); /**/
	
		AppController appPresenter = injector.getAppPresenter();
		appPresenter.go(RootLayoutPanel.get());
		
	}
}