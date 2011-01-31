package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.service.DatesService;
import org.gwtcom.server.dao.DateItemDao;
import org.gwtcom.shared.DateItemRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("datesService")
@Transactional(readOnly = true)
public class DatesServiceImpl implements DatesService {

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
	@Transactional(readOnly = false)
	public void removeDateItem(DateItemRemote item) {
		_datesItemDao.deleteDateItem(item);
	}

	@Override
	public DateItemRemote getDateItem(String id) {
		return _datesItemDao.getDateItem(id);
	}

}
