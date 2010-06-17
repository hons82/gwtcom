package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.NewsDetail;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Document service async interface.
 * 
 * @author See Wah Cheng
 * @created 5 Jun 2009
 */
public interface NewsServiceAsync {

	public void getPublicNews(AsyncCallback<List<NewsDetail>> callback);

	public void getPrivateNews(AsyncCallback<List<NewsDetail>> callback);

}
