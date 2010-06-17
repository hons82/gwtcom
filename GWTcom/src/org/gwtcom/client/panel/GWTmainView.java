package org.gwtcom.client.panel;

import org.gwtcom.client.panel.news.NewsList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

public class GWTmainView extends ResizeComposite{

	interface Binder extends UiBinder<DockLayoutPanel, GWTmainView> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TopPanel topPanel;
	@UiField
	Shortcuts shortcuts;
	@UiField
	LayoutPanel container;

	private DockLayoutPanel _outer;

	public GWTmainView(){
		// Inject global styles.
		// GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();

		// Create the UI defined in GWTcom.ui.xml.
		_outer = binder.createAndBindUi(this);
		
		// Special-case stuff to make topPanel overhang a bit.
		Element topElem = _outer.getWidgetContainerElement(topPanel);
		topElem.getStyle().setZIndex(2);
		topElem.getStyle().setOverflow(Overflow.VISIBLE);


				//initView();
//		RootLayoutPanel root = RootLayoutPanel.get();
//		root.clear();
//		root.add(outer);
		// TODO: test
//		shortcuts.addNavigationMenuChangedHandler(new INavigationMenuItemChange() {
//
//			@Override
//			public void onCategoryChange(NavigationMenuChangeEvent event) {
//				// TODO Auto-generated method stub
//				System.out.println("back at home");
//				initView();
//			}
//		});
	}
	
	/**
	 * 
	 */
	private void initView() {
		if (container.getWidgetCount() > 0 && container.getWidget(0) instanceof NewsList) {
			container.clear();
			container.add(new Shortcuts());
		} else {
			container.clear();
			container.add(new NewsList());
		}
	}

	public HasWidgets asWidget(){
		return _outer;
	}
	
	public LayoutPanel getDetailContainer(){
		return container;
	}

}
