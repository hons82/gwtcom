package org.gwtcom.server.dao;

import java.util.List;

import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;
import org.gwtcom.shared.UserLoginRemote;

public interface NewsItemDao extends GenericDao<NewsItem, String> {

	public List<NewsItemRemote> getPublicNews();

	public NewsItemRemote getNewsItem(String id);

	public boolean deleteNewsItem(NewsItemRemote item, UserLoginRemote loggedInUserRemote);

	public NewsItemRemote addNewsItem(UserLoginRemote loggedInUserRemote);

	boolean updateNewsItem(NewsItemRemote newsItem, UserLoginRemote loggedInUserRemote);

}
