package org.gwtcom.client.panel.event;

import com.google.gwt.event.shared.GwtEvent;

public class NavigationMenuChangeEvent extends GwtEvent<INavigationMenuItemChange> {


	    public NavigationMenuChangeEvent() {
	        super();
	    }

	    public static final Type<INavigationMenuItemChange> TYPE = new Type<INavigationMenuItemChange>();

	    @Override
	    protected void dispatch(INavigationMenuItemChange handler) {
	        handler.onCategoryChange(this);
	    }

	    @Override
	    public com.google.gwt.event.shared.GwtEvent.Type<INavigationMenuItemChange> getAssociatedType() {
	        return TYPE;
	    }

}
