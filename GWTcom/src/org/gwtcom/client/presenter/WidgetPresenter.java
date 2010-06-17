package org.gwtcom.client.presenter;

import org.gwtcom.client.event.EventBus;

public abstract class WidgetPresenter<D extends WidgetDisplay> extends BasicPresenter<D> {
    public WidgetPresenter( D display, EventBus eventBus ) {
        super( display, eventBus );
    }
}