package org.gwtcom.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface INavigationMenuItemChange extends EventHandler {
	
	void onCategoryChange(NavigationMenuChangeEvent event);

}
