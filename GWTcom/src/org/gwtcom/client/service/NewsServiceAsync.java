package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NewsServiceAsync {

	public void getPublicNews(AsyncCallback<List<NewsItemRemote>> callback);

	public void getPrivateNews(AsyncCallback<List<NewsItemRemote>> callback);

	public void getNewsItem(String item, AsyncCallback<NewsItemRemote> callback);

	public void removeNewsItem(NewsItemRemote item, AsyncCallback<Void> callback);

	public void updateNewsItem(NewsItemRemote selectedItem, String contentasHTML, AsyncCallback<Boolean> asyncCallback);

}
