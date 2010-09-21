package org.gwtcom.client.panel.navigation;

import org.gwtcom.client.event.bus.EventBus;
import org.gwtcom.shared.UserLoginRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.StackLayoutPanel;

/**
 * A composite that contains the shortcut stack panel on the left side. The
 * mailbox tree and shortcut lists don't actually do anything, but serve to show
 * how you can construct an interface using
 * {@link com.google.gwt.user.client.ui.StackPanel},
 * {@link com.google.gwt.user.client.ui.Tree}, and other custom widgets.
 */
public class Shortcuts extends ResizeComposite {

	interface Binder extends UiBinder<StackLayoutPanel, Shortcuts> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided=true)
	PublicMenu publicMenu;
	@UiField(provided=true)
	PrivateMenu privateMenu;

	private EventBus _eventbus;

	/**
	 * Constructs a new shortcuts widget using the specified images.
	 * @param eventbus 
	 * 
	 * @param images
	 *            a bundle that provides the images for this widget
	 */
	public Shortcuts(EventBus eventbus) {
		_eventbus = eventbus;
		
		publicMenu = new PublicMenu(eventbus);
		privateMenu = new PrivateMenu(eventbus);
		initWidget(binder.createAndBindUi(this));
		
		initView();
	}
	
	private void initView(){
//		privateMenu.addNavigationMenuChangedHandler(new INavigationMenuChangeEvent() {
//
//			@Override
//			public void onCategoryChange(NavigationMenuChangeEvent event) {
//				// TODO Auto-generated method stub
//				System.out.println("EventType: "+event.getAssociatedType());
//				
//			}});
	
	}

	public void setLoggedIn(UserLoginRemote result) {
		publicMenu.setLoggedIn(result);
		privateMenu.setLoggedIn(result);
	}
	
}