package org.gwtcom.server.dao;


import java.util.List;

import org.gwtcom.server.domain.DateItem;
import org.gwtcom.shared.DateItemRemote;

public interface DateItemDao extends GenericDao<DateItem, String>{

	public List<DateItemRemote> getPublicDates();

	public DateItemRemote getDateItem(String id);

	public void deleteDateItem(DateItemRemote item);

}
