package org.gwtcom.server.dao;


import java.util.List;

import org.gwtcom.server.domain.DateItem;
import org.gwtcom.shared.DateItemRemote;
import org.gwtcom.shared.UserLoginRemote;

public interface DateItemDao extends GenericDao<DateItem, String>{

	public List<DateItemRemote> getPublicDates();

	public DateItemRemote getDateItem(String id);

	boolean deleteDateItem(DateItemRemote item, UserLoginRemote loggedInUserRemote);

}
