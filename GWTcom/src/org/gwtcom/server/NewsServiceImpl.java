package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.gwtcom.client.service.NewsService;
import org.gwtcom.server.domain.NewsItem;
import org.gwtcom.shared.NewsItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	// TODO: this is just a test
	private boolean _first;

	protected EntityManager entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public JpaTemplate getTemplate() {
		return new JpaTemplate(entityManager);
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
	@Secured("ROLE_ADMIN")
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
			ret.add(new NewsItemRemote(item.getId(), item.getDateAdded(), item.getAuthor(), item.getTitle()));
		}
		return ret;
	}

	private void createCustomer() {
		EntityTransaction trx = entityManager.getTransaction();
		NewsItem newNewsItem = new NewsItem();
		newNewsItem.setAuthor("Hannes Tribus " + System.currentTimeMillis());
		newNewsItem.setTitle("Die Auswirkungen der Sonnenstrahlen auf das Liebesleben der Pflastersteine " + System.currentTimeMillis());
		newNewsItem.setDateAdded(new Date(System.currentTimeMillis()));
		trx.begin();
		entityManager.persist(newNewsItem);
		trx.commit();
	}

	private void removeNewsItem(NewsItem item) {
		EntityTransaction trx = entityManager.getTransaction();
		trx.begin();
		entityManager.remove(item);
		trx.commit();
	}

	private Collection<NewsItem> getNewsItems() {
		return getTemplate().execute(new JpaCallback<Collection<NewsItem>>() {
			@SuppressWarnings("unchecked")
			@Override
			public Collection<NewsItem> doInJpa(EntityManager arg0) throws PersistenceException {
				Collection<NewsItem> resultList = (Collection<NewsItem>) entityManager.createQuery(
						"SELECT news FROM org.gwtcom.server.domain.NewsItem news").getResultList();
				return resultList;
			}
		});
	}

	@Override
	public NewsItemRemote getNewsItem(Long id) {
		System.out.println("Check for id <" + id + ">");
		NewsItem item = getNewsItembyID(id);
		if (item != null) {
			System.out.println("item != null");
			return new NewsItemRemote(item.getId(), item.getDateAdded(), item.getAuthor(), item.getTitle());
		}
		System.out.println("item == null");
		return null;
	}

	public NewsItem getNewsItembyID(final Long id) {
		return getTemplate().execute(new JpaCallback<NewsItem>() {
			@SuppressWarnings("unchecked")
			@Override
			public NewsItem doInJpa(EntityManager arg0) throws PersistenceException {
				Number count = (Number) entityManager.createQuery(
						"SELECT count(distinct _id) FROM org.gwtcom.server.domain.NewsItem news WHERE news._id =" + id.toString()).getSingleResult();
				System.out.println("int number " + count.intValue());
				if (count.intValue() == 1) {
					NewsItem result = (NewsItem) entityManager.createQuery(
							"SELECT news FROM org.gwtcom.server.domain.NewsItem news WHERE news._id =" + id.toString()).getSingleResult();
					return result;
				} else
					return null;
			}
		});
	}

}
