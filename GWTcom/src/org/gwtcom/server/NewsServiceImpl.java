package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.server.converter.NewsItemConverter;
import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	// TODO: this is just a test
	private boolean _first;

	private NewsItemConverter newsItemConverter;

	protected EntityManager entityManager;

	@Autowired
	public void setentityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Autowired
	public void setNewsItemConverter(NewsItemConverter newsItemConverter) {
		this.newsItemConverter = newsItemConverter;
	}

	public NewsServiceImpl() {
		_first = false;
	}

	@Override
	@Secured("ROLE_ADMIN")
	public List<NewsItemRemote> getPrivateNews() {
		List<NewsItemRemote> ret = getPublicNews();
		return ret;
	}

	/**
	 * @return
	 */
	@Override
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
			ret.add(newsItemConverter.convertDomainToRemote(item));
		}
		return ret;
	}

	private void createCustomer() {
		EntityTransaction tx = entityManager.getTransaction();
		NewsItem newNewsItem = new NewsItem();
		newNewsItem.setAuthor(null);
		newNewsItem.setTitle("Die Auswirkungen der Sonnenstrahlen auf das Liebesleben der Pflastersteine "
				+ System.currentTimeMillis());
		newNewsItem.setDateAdded(new Date(System.currentTimeMillis()));
		tx.begin();
		entityManager.persist(newNewsItem);
		tx.commit();
	}

	@Secured("ROLE_ADMIN")
	private void removeNewsItem(NewsItem item) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.remove(item);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	private Collection<NewsItem> getNewsItems() {
		Collection<NewsItem> resultList = entityManager.createQuery("SELECT FROM " + NewsItem.class.getName())
				.getResultList();
		return resultList;
	}

	@Override
	public NewsItemRemote getNewsItem(Long id) {
		System.out.println("Check for id <" + id + ">");
		NewsItem item = getNewsItembyID(id);
		if (item != null) {
			System.out.println("item != null");
			return newsItemConverter.convertDomainToRemote(item);
		}
		System.out.println("item == null");
		return null;
	}

	public NewsItem getNewsItembyID(final Long id) {
		return entityManager.find(NewsItem.class, KeyFactory.createKey(NewsItem.class.getSimpleName(), id));
	}

}
