package org.gwtcom.client.view.news;

import java.util.List;

import org.gwtcom.client.view.navigation.NavBar;
import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class NewsListImpl extends ResizeComposite implements NewsList{

	interface Binder extends UiBinder<Widget, NewsListImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	static final int VISIBLE_EMAIL_COUNT = 20;

	@UiField
	FlexTable header;
	@UiField
	FlexTable table;
	@UiField
	SelectionStyle selectionStyle;

	private int startIndex, selectedRow = -1;
	private final NavBar navBar;

	private Presenter _presenter;

	public NewsListImpl() {
		initWidget(binder.createAndBindUi(this));
		navBar = new NavBar();

		initTable();
	}

	@Override
	protected void onLoad() {
		// Select the first row if none is selected.
		if (selectedRow == -1) {
			selectRow(0);
		}
	}

	@Override
	public void newer(List<NewsItemRemote> data) {
		// Move back a page.
		startIndex -= VISIBLE_EMAIL_COUNT;
		if (startIndex < 0) {
			startIndex = 0;
		} else {
			styleRow(selectedRow, false);
			selectedRow = -1;
		}
		update(data);
		selectRow(0);
	}

	@Override
	public void older(List<NewsItemRemote> data) {
		// Move forward a page.
		startIndex += VISIBLE_EMAIL_COUNT;
		// TODO
		if (startIndex >= data.size()) {
			startIndex -= VISIBLE_EMAIL_COUNT;
		} else {
			styleRow(selectedRow, false);
			selectedRow = -1;
		}
		update(data);
		selectRow(0);
	}

	@UiHandler("table")
	void onTableClicked(ClickEvent event) {
		// Select the row that was clicked (-1 to account for header row).
		Cell cell = table.getCellForEvent(event);
		if (cell != null) {
			int row = cell.getRowIndex();
			selectRow(row);
		}
	}

	/**
	 * Initializes the table so that it contains enough rows for a full page of
	 * emails. Also creates the images that will be used as 'read' flags.
	 */
	private void initTable() {
		// Initialize the header.
		header.getColumnFormatter().setWidth(0, "50px");
		header.getColumnFormatter().setWidth(1, "200px");
		header.getColumnFormatter().setWidth(2, "200px");
		header.getColumnFormatter().setWidth(3, "56px");

		header.setText(0, 0, "ID");
		header.setText(0, 1, "Author");
		header.setText(0, 2, "Date Added");
		header.setText(0, 3, "Title");
		header.setWidget(0, 4, navBar);

		header.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		header.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
		header.getCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		header.getCellFormatter().setVerticalAlignment(0, 3, HasVerticalAlignment.ALIGN_TOP);
		header.getCellFormatter().setVerticalAlignment(0, 4, HasVerticalAlignment.ALIGN_TOP);

		header.getCellFormatter().setHorizontalAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT);

		// Initialize the table.
		table.getColumnFormatter().setWidth(0, "50px");
		table.getColumnFormatter().setWidth(1, "200px");
		table.getColumnFormatter().setWidth(2, "200px");
		// table.getColumnFormatter().setWidth(3, "256px");
	}

	/**
	 * Selects the given row (relative to the current page).
	 * 
	 * @param row
	 *            the row to be selected
	 */
	private void selectRow(int row) {
		// When a row (other than the first one, which is used as a header) is
		// selected, display its associated NewsItem.
		if (startIndex + row >= table.getRowCount()) {
			return;
		}

		styleRow(selectedRow, false);
		styleRow(row, true);

		selectedRow = row;
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			String style = selectionStyle.selectedRow();

			if (selected) {
				table.getRowFormatter().addStyleName(row, style);
			} else {
				table.getRowFormatter().removeStyleName(row, style);
			}
		}
	}

	@Override
	public HasClickHandlers getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(List<NewsItemRemote> data) {
		// TODO Auto-generated method stub
		System.out.println(">>>>> NewsList.setData");
		// startIndex = 0;
		update(data);
		if (selectedRow == -1) {
			selectRow(0);
		}
	}

	/**
	 * @param data
	 * 
	 */
	private void update(List<NewsItemRemote> data) {
		// Update the older/newer buttons & label.
		int count = data.size();
		int max = startIndex + VISIBLE_EMAIL_COUNT;
		if (max > count) {
			max = count;
		}

		// Update the nav bar.
		navBar.update(startIndex, count, max);

		// Show the selected emails.
		int i = 0;
		for (i = 0; i < VISIBLE_EMAIL_COUNT; ++i) {
			// Don't read past the end.
			if (startIndex + i >= data.size()) {
				break;
			}

			NewsItemRemote item = data.get(startIndex + i);

			DateTimeFormat fmt = DateTimeFormat.getFormat("EEE. dd MMM. yyyy HH:mm:ss");
			// Add a new row to the table, then set each of its columns to the
			// email's sender and subject values.
			table.setText(i, 0, Long.toString(item.getId()));
			table.setText(i, 1, item.getAuthor()!=null?item.getAuthor().getSurname()+" "+item.getAuthor().getName():"<empty>");
			table.setText(i, 2, fmt.format(item.getDateAdded()));
			table.setText(i, 3, item.getTitle());

			table.getCellFormatter().setVerticalAlignment(i, 0, HasVerticalAlignment.ALIGN_TOP);
			table.getCellFormatter().setVerticalAlignment(i, 1, HasVerticalAlignment.ALIGN_TOP);
			table.getCellFormatter().setVerticalAlignment(i, 2, HasVerticalAlignment.ALIGN_TOP);
			table.getCellFormatter().setVerticalAlignment(i, 3, HasVerticalAlignment.ALIGN_TOP);
		}

		// Clear any remaining slots.
		for (int j = i; j < VISIBLE_EMAIL_COUNT; ++j) {
			if (i >= table.getRowCount()) {
				break;
			}
			table.removeRow(i);
		}

	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void startProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopProcessing() {
		// TODO Auto-generated method stub

	}

	@Override
	public FlexTable getNewsListTable() {
		return table;
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selectedRow = -1;
		HTMLTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			// Suppress clicks if the user is actually selecting the
			// check box
			//
			if (cell.getCellIndex() > 0) {
				selectedRow = cell.getRowIndex();
			}
		}

		return selectedRow;
	}

	@Override
	public NavBar getNavBar() {
		return navBar;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		_presenter = presenter;
	}

}