package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Document service async interface.
 * 
 * @author See Wah Cheng
 * @created 5 Jun 2009
 */
public interface NewsServiceAsync {

	public void getPublicNews(AsyncCallback<List<NewsItemRemote>> callback);

	public void getPrivateNews(AsyncCallback<List<NewsItemRemote>> callback);

}
