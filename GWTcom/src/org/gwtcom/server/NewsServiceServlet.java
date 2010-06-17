package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.ServiceSecurityException;
import org.gwtcom.client.service.NewsService;
import org.gwtcom.shared.NewsDetail;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class NewsServiceServlet extends DependencyInjectionRemoteServiceServlet implements NewsService {

	@Autowired
	NewsService newsService;

	public List<NewsDetail> getPrivateNews() throws ServiceSecurityException {
		try {
			return newsService.getPrivateNews();
		} catch (Throwable e) {
			throw new ServiceSecurityException();
		}
	}

	public List<NewsDetail> getPublicNews() {
		return newsService.getPublicNews();
	}

}
