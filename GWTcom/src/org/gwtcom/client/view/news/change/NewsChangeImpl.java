package org.gwtcom.client.view.news.change;

import org.gwtcom.client.view.navigation.ShowMorePagerPanel;
import org.gwtcom.client.view.widget.TitleContentEditWidget;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class NewsChangeImpl extends ResizeComposite implements NewsChange {

	static class NewsCell extends AbstractCell<NewsItemRemote> {

		/**
		 * The html of the image used for contacts.
		 */
		private final String imageHtml;

		public NewsCell() {
			this.imageHtml = "";
		}

		public NewsCell(ImageResource image) {
			this.imageHtml = AbstractImagePrototype.create(image).getHTML();
		}

		@Override
		public void render(Context context, NewsItemRemote value, SafeHtmlBuilder sb) {
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
					&& (value.getAuthor().getLastname() != null || value.getAuthor().getFirstname() != null) ? (value.getAuthor()
					.getLastname() != null ? value.getAuthor().getLastname() : "")
					+ " "
					+ (value.getAuthor().getFirstname() != null ? value.getAuthor().getFirstname() : "") : "<anonymous>");
			sb.appendHtmlConstant("</td></tr><tr><td>");
			sb.appendEscaped(value.getTitle());
			sb.appendHtmlConstant("</td></tr></table>");
		}

	}

	interface Binder extends UiBinder<Widget, NewsChangeImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField
	ShowMorePagerPanel pagerPanel;
	@UiField
	TitleContentEditWidget content;

	@UiField
	PushButton addBtn;
	@UiField
	PushButton removeBtn;
	@UiField
	PushButton saveBtn;
	@UiField
	PushButton cancelBtn;

	private final CellList<NewsItemRemote> cellList;
	private NewsItemRemote _selectedItem;

	private Presenter _presenter;

	public NewsChangeImpl() {
		System.out.println(">>>>>NewsChangeImpl()");

		// Create a CellList.
		NewsCell newsCell = new NewsCell();

		// Set a key provider that provides a unique key for each contact. If key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellList = new CellList<NewsItemRemote>(newsCell, NewsItemRemote.KEY_PROVIDER);
		cellList.setPageSize(10);
		cellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<NewsItemRemote> selectionModel = new SingleSelectionModel<NewsItemRemote>(
				NewsItemRemote.KEY_PROVIDER);
		cellList.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@SuppressWarnings("unchecked")
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				if (event.getSource() instanceof SingleSelectionModel<?>) {
					NewsItemRemote dest = ((SingleSelectionModel<NewsItemRemote>) event.getSource()).getSelectedObject();
					if (dest != null) {
						content.setTitle(dest.getTitle());
						content.setEnabled(true);
						content.setContentAsHTML(dest.getContent());
						_selectedItem = dest;
					} else {
						content.setTitle("");
						content.setEnabled(false);
						content.setContent("");
						_selectedItem = null;
					}
				}
			}
		});
		initWidget(binder.createAndBindUi(this));

		pagerPanel.setDisplay(cellList);

		cellList.setFocus(false);
	}

	@Override
	public void addButtonClickHandler(ClickHandler clickHandler) {
		addBtn.addClickHandler(clickHandler);
	}

	@Override
	public void removeButtonClickHandler(ClickHandler clickHandler) {
		removeBtn.addClickHandler(clickHandler);
	}

	@Override
	public void cancelButtonClickHandler(ClickHandler clickHandler) {
		cancelBtn.addClickHandler(clickHandler);
	}

	@Override
	public void saveButtonClickHandler(ClickHandler clickHandler) {
		saveBtn.addClickHandler(clickHandler);
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
	public CellList<NewsItemRemote> getNewsList() {
		return cellList;
	}

	@Override
	public NewsItemRemote getSelectedItem() {
		return _selectedItem;
	}

	@Override
	public String getNewsTitle() {
		return content.getTitle();
	}

	@Override
	public String getContentasHTML() {
		return content.getContentasHTML();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}

}