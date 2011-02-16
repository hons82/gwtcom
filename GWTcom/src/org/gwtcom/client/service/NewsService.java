package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service/newsService")
public interface NewsService extends RemoteService {

	/**
	 * Returns the number of public publications.
	 * 
	 * @return
	 */
	List<NewsItemRemote> getAllNews();

	NewsItemRemote getNewsItem(String item);

	boolean deleteNewsItem(NewsItemRemote item);

	boolean updateNewsItem(NewsItemRemote selectedItem);

	NewsItemRemote addNewsItem();

}
