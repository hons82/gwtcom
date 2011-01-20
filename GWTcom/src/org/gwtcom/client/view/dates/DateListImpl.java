package org.gwtcom.client.view.dates;

import org.gwtcom.client.place.DateItemPlace;
import org.gwtcom.client.view.navigation.ShowMorePagerPanel;
import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class DateListImpl extends ResizeComposite implements DateList {

	static class DateCell extends AbstractCell<DateItemRemote> {

		/**
		 * The html of the image used for contacts.
		 */
		private final String imageHtml;

		public DateCell() {
			this.imageHtml = "";
		}

		public DateCell(ImageResource image) {
			this.imageHtml = AbstractImagePrototype.create(image).getHTML();
		}

		@Override
		public void render(Context context, DateItemRemote value, SafeHtmlBuilder sb) {
			// Value can be null, so do a null check..
			if (value == null) {
				return;
			}

			sb.appendHtmlConstant("<table>");

			// Add the contact image.
			sb.appendHtmlConstant("<tr><td rowspan='3'>");
			sb.appendHtmlConstant(imageHtml);
			sb.appendHtmlConstant("</td>");

			// Add the name and address.
			sb.appendHtmlConstant("<td style='font-size:95%;'>");
			sb.appendEscaped(value.getAuthor() != null
					&& (value.getAuthor().getSurname() != null || value.getAuthor().getName() != null) ? (value.getAuthor()
					.getSurname() != null ? value.getAuthor().getSurname() : "")
					+ " "
					+ (value.getAuthor().getName() != null ? value.getAuthor().getName() : "") : "<anonymous>");
			sb.appendHtmlConstant("</td></tr><tr><td>");
			sb.appendEscaped(value.getTitle());
			sb.appendHtmlConstant("</td></tr></table>");
		}

	}

	interface Binder extends UiBinder<Widget, DateListImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	ShowMorePagerPanel pagerPanel;

	private final CellList<DateItemRemote> cellList;

	private Presenter _presenter;

	public DateListImpl() {
		// Create a CellList.
		DateCell contactCell = new DateCell();

		// Set a key provider that provides a unique key for each contact. If key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellList = new CellList<DateItemRemote>(contactCell, DateItemRemote.KEY_PROVIDER);
		cellList.setPageSize(10);
		cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<DateItemRemote> selectionModel = new SingleSelectionModel<DateItemRemote>(
				DateItemRemote.KEY_PROVIDER);
		cellList.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@SuppressWarnings("unchecked")
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (event.getSource() instanceof SingleSelectionModel<?>) {
					DateItemRemote dest = ((SingleSelectionModel<DateItemRemote>) event.getSource()).getSelectedObject();
					if (dest != null)
						_presenter.goTo(new DateItemPlace(dest));
				}
			}
		});
		initWidget(binder.createAndBindUi(this));

		pagerPanel.setDisplay(cellList);

		cellList.setFocus(false);
	}

	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public CellList<DateItemRemote> getDateList() {
		return cellList;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}

}