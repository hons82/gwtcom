package org.gwtcom.server.dao.gaeimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtcom.server.converter.gaeimpl.WallEntryConverter;
import org.gwtcom.server.dao.UserLoginDao;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.dao.WallEntryDao;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.Key;

@Repository("wallEntryDao")
public class WallEntryDaoGaeImpl extends GenericDaoGaeImpl<WallEntry, String> implements WallEntryDao {
	
	@Autowired
	private WallEntryConverter _wallEntryConverter;
	@Autowired
	protected UserLoginDao _userLoginDao;
	@Autowired
	protected UserProfileDao _userProfileDao;

	@Override
	public List<WallEntryRemote> getPublicWallEntries(String userLoginId) {
		List<WallEntryRemote> ret = new ArrayList<WallEntryRemote>();

		UserLogin login = _userLoginDao.retrieve(userLoginId);

		for (Key key : login.getUserprofile().getWall()) {
			WallEntry item = _entityManager.find(WallEntry.class, key);
			ret.add(_wallEntryConverter.convertDomainToRemote(item));
		}
		return ret;
	}

	@Override
	@Transactional(readOnly = false)
	public WallEntryRemote addWallPost(String userLoginId, String loggedInUserId, String content) {
		// TODO: There should be a better way

		// gather all information about the author
		UserLogin loggedInUser = _userLoginDao.retrieve(loggedInUserId);
		UserProfile loggedInUserProfile = loggedInUser != null ? loggedInUser.getUserprofile() : null;
		WallEntry wallEntry = new WallEntry();

		if (loggedInUser != null && loggedInUserProfile != null) {
			// gather all needed information about the owner of the wall
			UserLogin wallUserLogin = _userLoginDao.retrieve(userLoginId);
			UserProfile wallUserProfile = wallUserLogin != null ? wallUserLogin.getUserprofile() : null;
			List<Key> wallUserWall = wallUserProfile != null ? wallUserProfile.getWall() : null;

			if (wallUserLogin != null && wallUserProfile != null && wallUserWall != null) {

				wallEntry.setOwner(wallUserProfile.getId());
				wallEntry.setAuthor(loggedInUserProfile != null ? loggedInUserProfile.getId() : null);
				wallEntry.setDateAdded(new Date());
				wallEntry.setContent(content);
				saveOrUpdate(wallEntry);

				wallUserWall.add(wallEntry.getId());
				wallUserProfile.setWall(wallUserWall);
				_userProfileDao.saveOrUpdate(wallUserProfile);

			}
		}
		return _wallEntryConverter.convertDomainToRemote(wallEntry);
	}
}
