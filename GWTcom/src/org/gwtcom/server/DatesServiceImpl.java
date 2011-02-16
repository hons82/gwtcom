package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.service.DatesService;
import org.gwtcom.server.dao.DateItemDao;
import org.gwtcom.shared.DateItemRemote;
import org.gwtcom.shared.UserLoginRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("datesService")
public class DatesServiceImpl extends AbstractUserAwareService implements DatesService {

	@Autowired
	private DateItemDao _datesItemDao;

	@Override
	@Secured("ROLE_ADMIN")
	public List<DateItemRemote> getPrivateDates() {
		List<DateItemRemote> ret = getPublicDates();
		return ret;
	}

	/**
	 * @return
	 */
	@Override
	public List<DateItemRemote> getPublicDates() {
		return _datesItemDao.getPublicDates();
	}

	@Override
	@Secured("ROLE_ADMIN")
	public boolean deleteDateItem(DateItemRemote item) {
		UserLoginRemote loggedInUserRemote = getUserLoginRemote();
		return _datesItemDao.deleteDateItem(item, loggedInUserRemote);
	}

	@Override
	public DateItemRemote getDateItem(String id) {
		return _datesItemDao.getDateItem(id);
	}

}
