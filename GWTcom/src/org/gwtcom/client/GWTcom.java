package org.gwtcom.client;

import org.gwtcom.client.gin.GWTcomGinjector;
import org.gwtcom.client.presenter.NewsListPresenter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTcom implements EntryPoint {
	
	private final GWTcomGinjector injector = GWT.create(GWTcomGinjector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Get rid of scrollbars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area.
		Window.enableScrolling(false);
		Window.setMargin("0px");
		
		injector.getPlaceManager();
		
	//If a presenter is providing a Place, I discovered that the presenter has to be
	//instantiated at the start of the application or else the Presenters will will not handle PlaceRequestEvent properly and history mechanism properly
	//This is the current behavior of  gwt-presenter release 1.0. I hope future version will provide lazy-loading of Presenters
	//
	/**/	NewsListPresenter cPresenter = injector.getNewsListPresenter(); /**/
//	/**/	EditContactPresenter ePresenter = injector.getEditContactPresenter(); /**/
	
		AppController appPresenter = injector.getAppPresenter();
		appPresenter.go(RootLayoutPanel.get());
		
	}
}