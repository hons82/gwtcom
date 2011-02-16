package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NewsServiceAsync {

	public void getAllNews(AsyncCallback<List<NewsItemRemote>> callback);

	public void getNewsItem(String item, AsyncCallback<NewsItemRemote> callback);

	public void deleteNewsItem(NewsItemRemote item, AsyncCallback<Boolean> callback);

	public void updateNewsItem(NewsItemRemote selectedItem, AsyncCallback<Boolean> asyncCallback);

	public void addNewsItem(AsyncCallback<NewsItemRemote> asyncCallback);

}
