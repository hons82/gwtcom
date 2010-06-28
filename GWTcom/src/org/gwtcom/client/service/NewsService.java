package org.gwtcom.client.service;


import java.util.List;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("newsService")
public interface NewsService extends RemoteService {

	/**
	 * Returns the number of public publications.
	 * 
	 * @return
	 */
	List<NewsItemRemote> getPublicNews();

	NewsItemRemote getNewsItem(Long item);
	
	/**
	 * Returns the number of private publications.
	 * 
	 * @return
	 * @exception ServiceSecurityException
	 */
	List<NewsItemRemote> getPrivateNews() throws ServiceSecurityException;

}
