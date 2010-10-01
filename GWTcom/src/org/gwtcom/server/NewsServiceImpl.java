package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	// TODO: this is just a test
	private boolean _first;

	protected PersistenceManager persistenceManager;

	@Autowired
	public void setpersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	public NewsServiceImpl() {
		_first = false;
	}

	public List<NewsItemRemote> getPrivateNews() {
		List<NewsItemRemote> ret = getPublicNews();
		return ret;
	}

	/**
	 * @return
	 */
	public List<NewsItemRemote> getPublicNews() {
		List<NewsItemRemote> ret = new ArrayList<NewsItemRemote>();
		// TODO: this is just a test
		for (NewsItem item : getNewsItems()) {
			if (!_first) {
				removeNewsItem(item);
			}
		}
		if (!_first) {
			for (int i = 0; i < 30; i++) {
				createCustomer();
			}
		}
		_first = true;
		//
		for (NewsItem item : getNewsItems()) {
			ret.add(new NewsItemRemote(item.getId().getId(), item.getDateAdded(), UserProfile.serializeUserProfile(item.getAuthor()), item.getTitle()));
		}
		return ret;
	}

	private void createCustomer() {
		Transaction tx = persistenceManager.currentTransaction();
		NewsItem newNewsItem = new NewsItem();
		newNewsItem.setAuthor(null);
		newNewsItem.setTitle("Die Auswirkungen der Sonnenstrahlen auf das Liebesleben der Pflastersteine " + System.currentTimeMillis());
		newNewsItem.setDateAdded(new Date(System.currentTimeMillis()));
		tx.begin();
		persistenceManager.makePersistent(newNewsItem);
		tx.commit();
	}

	@Secured("ROLE_ADMIN")
	private void removeNewsItem(NewsItem item) {
		Transaction tx = persistenceManager.currentTransaction();
		tx.begin();
		persistenceManager.deletePersistent(item);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	private Collection<NewsItem> getNewsItems() {
		Collection<NewsItem> resultList = (Collection<NewsItem>) persistenceManager.newQuery("SELECT FROM " + NewsItem.class.getName()).execute();
		return resultList;
	}

	@Override
	public NewsItemRemote getNewsItem(Long id) {
		System.out.println("Check for id <" + id + ">");
		NewsItem item = getNewsItembyID(id);
		if (item != null) {
			System.out.println("item != null");
			return new NewsItemRemote(item.getId().getId(), item.getDateAdded(), UserProfile.serializeUserProfile(item.getAuthor()), item.getTitle());
		}
		System.out.println("item == null");
		return null;
	}

	public NewsItem getNewsItembyID(final Long id) {
		return (NewsItem) persistenceManager.getObjectById(NewsItem.class, KeyFactory.createKey(NewsItem.class.getSimpleName(), id));
	}

}
