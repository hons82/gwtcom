package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.gwtcom.client.service.DatesService;
import org.gwtcom.server.domain.DateItem;
import org.gwtcom.shared.DateItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;

@Service("datesService")
public class DatesServiceImpl implements DatesService {

	// TODO: this is just a test
	private boolean _first;

	protected EntityManager entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public DatesServiceImpl() {
		_first = false;
	}

	@Override
	public List<DateItemRemote> getPrivateDates() {
		List<DateItemRemote> ret = getPublicDates();
		return ret;
	}

	/**
	 * @return
	 */
	@Override
	public List<DateItemRemote> getPublicDates() {
		List<DateItemRemote> ret = new ArrayList<DateItemRemote>();
		// TODO: this is just a test
		for (DateItem item : getDateItems()) {
			if (!_first) {
				removeDateItem(item);
			}
		}
		if (!_first) {
			for (int i = 0; i < 30; i++) {
				createCustomer();
			}
		}
		_first = true;
		//
		for (DateItem item : getDateItems()) {
			ret.add(new DateItemRemote(item.getId().getId(), item.getDateAdded(), ProfileServiceImpl
					.serializeUserProfile(item.getAuthor()), item.getTitle()));
		}
		return ret;
	}

	private void createCustomer() {
		EntityTransaction tx = entityManager.getTransaction();
		DateItem newDateItem = new DateItem();
		newDateItem.setAuthor(null);
		newDateItem.setTitle("Some Event " + System.currentTimeMillis());
		newDateItem.setDateAdded(new Date(System.currentTimeMillis()));
		tx.begin();
		entityManager.persist(newDateItem);
		tx.commit();
	}

	@Secured("ROLE_ADMIN")
	private void removeDateItem(DateItem item) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.remove(item);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	private Collection<DateItem> getDateItems() {
		Collection<DateItem> resultList = entityManager.createQuery(
				"SELECT FROM " + DateItem.class.getName()).getResultList();
		return resultList;
	}

	@Override
	public DateItemRemote getDateItem(Long id) {
		System.out.println("Check for id <" + id + ">");
		DateItem item = getDateItembyID(id);
		if (item != null) {
			System.out.println("item != null");
			return new DateItemRemote(item.getId().getId(), item.getDateAdded(),
					ProfileServiceImpl.serializeUserProfile(item.getAuthor()), item.getTitle());
		}
		System.out.println("item == null");
		return null;
	}

	public DateItem getDateItembyID(final Long id) {
		return entityManager.find(DateItem.class, KeyFactory.createKey(DateItem.class.getSimpleName(), id));
	}

}
