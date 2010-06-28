package org.gwtcom.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NavigationMenuChangeEvent extends GwtEvent<INavigationMenuChangeEvent>{

	public static final Type<INavigationMenuChangeEvent> TYPE = new Type<INavigationMenuChangeEvent>();
	
	@Override
	public Type<INavigationMenuChangeEvent> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(INavigationMenuChangeEvent handler) {
		handler.onCategoryChange(this);
	}

}
