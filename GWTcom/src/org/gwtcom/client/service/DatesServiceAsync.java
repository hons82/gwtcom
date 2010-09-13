package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatesServiceAsync {

	public void getPublicDates(AsyncCallback<List<DateItemRemote>> callback);

	public void getPrivateDates(AsyncCallback<List<DateItemRemote>> callback);

	void getDateItem(Long item, AsyncCallback<DateItemRemote> callback);

}
