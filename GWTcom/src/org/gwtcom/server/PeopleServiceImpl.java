package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.service.PeopleService;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.shared.FriendEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("peopleService")
public class PeopleServiceImpl extends AbstractUserAwareService implements PeopleService {

	@Autowired
	private UserProfileDao _userProfileDao;

	public PeopleServiceImpl() {
	}

	@Override
	public List<FriendEntryRemote> getPeople(String pattern) {
		return _userProfileDao.getPeople(pattern);
	}

}
