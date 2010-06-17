package org.gwtcom.client.place;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceChangedEvent extends GwtEvent<PlaceChangedHandler> {

    private static Type<PlaceChangedHandler> TYPE;

    public static Type<PlaceChangedHandler> getType() {
        if ( TYPE == null )
            TYPE = new Type<PlaceChangedHandler>();
        return TYPE;
    }

    private final PlaceRequest request;

    public PlaceChangedEvent( PlaceRequest request ) {
        this.request = request;
    }

    @Override
    protected void dispatch( PlaceChangedHandler handler ) {
        handler.onPlaceChange( this );
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<PlaceChangedHandler> getAssociatedType() {
        return getType();
    }

    public PlaceRequest getRequest() {
        return request;
    }
}