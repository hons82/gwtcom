package org.gwtcom.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NavigationMenuChangeEvent extends GwtEvent<INavigationMenuItemChange> {

		public static final Type<INavigationMenuItemChange> TYPE = new Type<INavigationMenuItemChange>();

	    public NavigationMenuChangeEvent() {
	        super();
	    }

	    @Override
	    protected void dispatch(INavigationMenuItemChange handler) {
	        handler.onCategoryChange(this);
	    }

	    @Override
	    public com.google.gwt.event.shared.GwtEvent.Type<INavigationMenuItemChange> getAssociatedType() {
	        return TYPE;
	    }

}
