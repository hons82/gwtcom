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

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class NewsServiceImpl extends RemoteServiceServlet implements NewsService {

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
	
	@Secured("ROLE_ADMIN")
	public List<NewsItemRemote>  getPrivateNews() {
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
			ret.add(new NewsItemRemote(item.getId(),item.getDateAdded(),item.getAuthor(),item.getTitle()));
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
				Collection<NewsItem> resultList = (Collection<NewsItem>) entityManager.createQuery("SELECT news FROM org.gwtcom.server.domain.NewsItem news").getResultList();
				return resultList;
			}
		});
	}

}
