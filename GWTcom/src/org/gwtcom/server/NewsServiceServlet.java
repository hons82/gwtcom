package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.client.service.ServiceSecurityException;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class NewsServiceServlet extends DependencyInjectionRemoteServiceServlet implements NewsService {

	@Autowired
	NewsService newsService;

	public List<NewsItemRemote> getPrivateNews() throws ServiceSecurityException {
		try {
			return newsService.getPrivateNews();
		} catch (Throwable e) {
			throw new ServiceSecurityException();
		}
	}

	public List<NewsItemRemote> getPublicNews() {
		return newsService.getPublicNews();
	}

}
