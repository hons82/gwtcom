package org.gwtcom.server;

import java.util.List;

import org.gwtcom.client.service.ProfileService;
import org.gwtcom.server.dao.UserLoginDao;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.dao.WallEntryDao;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("profileService")
public class ProfileServiceImpl extends AbstractUserAwareService implements ProfileService {

	@Autowired
	private UserProfileDao _userProfileDao;
	@Autowired
	private UserLoginDao _userLoginDao;
	@Autowired
	private WallEntryDao _wallEntryDao;

	public ProfileServiceImpl() {
	}

	@Override
	public UserProfileRemote getUserProfile(String userProfileId) {
		return _userProfileDao.getUserProfile(userProfileId);
	}

	@Override
	public UserProfileRemote getUserProfileByUserLoginId(String userLoginId) {
		return _userProfileDao.getUserProfile(_userLoginDao.retrieve(userLoginId));
	}

	@Override
	public List<WallEntryRemote> getPublicWallEntries(String userLoginId) {
		return _wallEntryDao.getPublicWallEntries(userLoginId);
	}

	@Override
	public WallEntryRemote addWallPost(String userLoginId, String content) {
		return _wallEntryDao.addWallPost(userLoginId, getUserLoginRemote().getId(), content);
	}

	@Override
	public boolean updateUserProfile(UserProfileRemote profile) {
		return _userProfileDao.updateUserProfile(profile);
	}

}
