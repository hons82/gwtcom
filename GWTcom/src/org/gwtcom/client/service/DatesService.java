package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.DateItemRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtcom/datesService")
public interface DatesService extends RemoteService {

	/**
	 * Returns the number of public publications.
	 * 
	 * @return
	 */
	List<DateItemRemote> getPublicDates();

	DateItemRemote getDateItem(String item);

	/**
	 * Returns the number of private publications.
	 * 
	 * @return
	 * @exception ServiceSecurityException
	 */
	List<DateItemRemote> getPrivateDates() throws ServiceSecurityException;

	void removeDateItem(DateItemRemote item);

}
