package org.gwtcom.server.dao;


import java.util.List;

import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;

public interface NewsItemDao extends GenericDao<NewsItem, String>{

	public List<NewsItemRemote> getPublicNews();

	public NewsItemRemote getNewsItem(String id);

	public void deleteNewsItem(NewsItemRemote item);

}
