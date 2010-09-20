package org.gwtcom.client.presenter;

import org.gwtcom.client.event.bus.EventBus;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class GeneralPresenter<D extends WidgetDisplay> extends WidgetPresenter<D>
{

	public GeneralPresenter(D display, EventBus eventBus)
	{
        super(display, eventBus );
	}
	
	public abstract void go(final HasWidgets container);
	
}


