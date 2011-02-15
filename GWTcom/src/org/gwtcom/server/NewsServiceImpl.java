package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.server.dao.NewsItemDao;
import org.gwtcom.shared.NewsItemRemote;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsServiceImpl extends AbstractUserAwareService implements NewsService {

	@Autowired
	private NewsItemDao _newsItemDao;

	@Override
	public List<NewsItemRemote> getAllNews() {
		return _newsItemDao.getPublicNews();
	}

	@Override
	@Secured("ROLE_ADMIN")
	public void deleteNewsItem(NewsItemRemote item) {
		_newsItemDao.deleteNewsItem(item);
	}

	@Override
	public NewsItemRemote getNewsItem(String id) {
		return _newsItemDao.getNewsItem(id);
	}

	@Override
	public boolean updateNewsItem(NewsItemRemote selectedItem, String contentasHTML) {
		UserLoginRemote loggedInUserRemote = getUserLoginRemote();
		return loggedInUserRemote != null ? _newsItemDao.updateNewsItemContent(loggedInUserRemote.getId(), selectedItem.getId(),
				contentasHTML) : false;
	}

	@Override
	@Secured("ROLE_ADMIN")
	public NewsItemRemote addNewsItem() {
		UserLoginRemote loggedInUserRemote = getUserLoginRemote();
		return _newsItemDao.addNewsItem(loggedInUserRemote.getId());
	}
}
