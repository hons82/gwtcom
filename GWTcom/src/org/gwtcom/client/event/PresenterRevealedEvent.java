package org.gwtcom.client.event;

import org.gwtcom.client.presenter.Presenter;

import com.google.gwt.event.shared.GwtEvent;

public class PresenterRevealedEvent extends GwtEvent<PresenterRevealedHandler> {

    private static final GwtEvent.Type<PresenterRevealedHandler> TYPE = new GwtEvent.Type<PresenterRevealedHandler>();

    private Presenter presenter;

    public static GwtEvent.Type<PresenterRevealedHandler> getType() {
        return TYPE;
    }

    public PresenterRevealedEvent( Presenter presenter ) {
        this.presenter = presenter;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected void dispatch( PresenterRevealedHandler handler ) {
        handler.onPresenterRevealed( this );
    }

    @Override
    public GwtEvent.Type<PresenterRevealedHandler> getAssociatedType() {
        return getType();
    }

}
