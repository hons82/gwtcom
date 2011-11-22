package org.gwtcom.client.view.people;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.widgets.tile.TileGrid;

public class PeopleViewImpl extends ResizeComposite implements PeopleView{

	interface Binder extends UiBinder<DockLayoutPanel, PeopleViewImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	TextBox inputContent;
	@UiField
	Button inputSubmit;
	@UiField(provided = true)
	TileGrid people;

	private Presenter _presenter;
	
	public PeopleViewImpl() {
		people = new TileGrid();
		initWidget(binder.createAndBindUi(this));
	}

	public String getInputContent() {
		return inputContent.getValue();
	}

	public void addPeopleClickHandler(ClickHandler clickHandler) {
		inputSubmit.addClickHandler(clickHandler);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}
}