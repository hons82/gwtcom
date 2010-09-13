package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.gwtcom.client.service.DatesService;
import org.gwtcom.server.domain.DateItem;
import org.gwtcom.shared.DateItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("datesService")
public class DatesServiceImpl implements DatesService {

	// TODO: this is just a test
	private boolean _first;

	protected PersistenceManager persistenceManager;

	@Autowired
	public void setEntityManager(PersistenceManager entityManager) {
		this.persistenceManager = entityManager;
	}

	public DatesServiceImpl() {
		_first = false;
	}

	public List<DateItemRemote> getPrivateDates() {
		List<DateItemRemote> ret = getPublicDates();
		return ret;
	}

	/**
	 * @return
	 */
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
			ret.add(new DateItemRemote(item.getId().getId(), item.getDateAdded(), item.getAuthor(), item.getTitle()));
		}
		return ret;
	}

	private void createCustomer() {
		Transaction tx = persistenceManager.currentTransaction();
		DateItem newDateItem = new DateItem();
		newDateItem.setAuthor("Hannes Tribus " + System.currentTimeMillis());
		newDateItem.setTitle("Some Event " + System.currentTimeMillis());
		newDateItem.setDateAdded(new Date(System.currentTimeMillis()));
		tx.begin();
		persistenceManager.makePersistent(newDateItem);
		tx.commit();
	}

	@Secured("ROLE_ADMIN")
	private void removeDateItem(DateItem item) {
		Transaction tx = persistenceManager.currentTransaction();
		tx.begin();
		persistenceManager.deletePersistent(item);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	private Collection<DateItem> getDateItems() {
		Collection<DateItem> resultList = (Collection<DateItem>) persistenceManager.newQuery(
				"SELECT Date FROM org.gwtcom.server.domain.DateItem Date").execute();
		return resultList;
	}

	@Override
	public DateItemRemote getDateItem(Long id) {
		System.out.println("Check for id <" + id + ">");
		DateItem item = getDateItembyID(id);
		if (item != null) {
			System.out.println("item != null");
			return new DateItemRemote(item.getId().getId(), item.getDateAdded(), item.getAuthor(), item.getTitle());
		}
		System.out.println("item == null");
		return null;
	}

	public DateItem getDateItembyID(final Long id) {
		Number count = (Number) persistenceManager.newQuery(
				"SELECT count(distinct _id) FROM org.gwtcom.server.domain.DateItem Date WHERE Date._id =" + id.toString()).execute();
		System.out.println("int number " + count.intValue());
		if (count.intValue() == 1) {
			DateItem result = (DateItem) persistenceManager.newQuery(
					"SELECT Date FROM org.gwtcom.server.domain.DateItem Date WHERE Date._id =" + id.toString()).execute();
			return result;
		} else
			return null;
	}

}