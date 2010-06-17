package org.gwtcom.client.service;


import java.util.List;

import org.gwtcom.shared.NewsItemRemote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface NewsService extends RemoteService {

	public static final String SERVICE_URI = "newsService";

	public static class Util {
		public static NewsServiceAsync getInstance() {
			NewsServiceAsync instance = (NewsServiceAsync) GWT
					.create(NewsService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}
	
	/**
	 * Returns the number of public publications.
	 * 
	 * @return
	 */
	List<NewsItemRemote> getPublicNews();

	/**
	 * Returns the number of private publications.
	 * 
	 * @return
	 * @exception ServiceSecurityException
	 */
	List<NewsItemRemote> getPrivateNews() throws ServiceSecurityException;

}
