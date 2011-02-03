package org.gwtcom.server.dao.gaeimpl;

import org.gwtcom.server.converter.gaeimpl.ProfileImageConverter;
import org.gwtcom.server.converter.gaeimpl.UserProfileConverter;
import org.gwtcom.server.dao.ProfileImageDao;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userProfileDao")
public class UserProfileDaoGaeImpl extends GenericDaoGaeImpl<UserProfile, String> implements UserProfileDao {

	@Autowired
	private UserProfileConverter _userProfileConverter;
	@Autowired
	private ProfileImageConverter _profileImageConverter;
	@Autowired
	private ProfileImageDao _profileImageDao;

	public UserProfileDaoGaeImpl() {
		super(UserProfile.class);
	}

	@Override
	public UserProfileRemote getUserProfile(String userProfileId) {
			return _userProfileConverter.convertDomainToRemote(retrieve(userProfileId));
	}

	@Override
	public UserProfileRemote getUserProfile(UserLogin userLogin) {
		//TODO
		return _userProfileConverter.convertDomainToRemote(userLogin != null ? userLogin.getUserprofile() : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean updateUserProfile(UserProfileRemote profile) {
		// Key parentkey = (Key) createId(UserLogin.class, profile.getParentId());
		// Key key = KeyFactory.createKey(parentkey, UserProfile.class.getSimpleName(), Long.parseLong(profile.getId()));
		UserProfile domain = retrieve(profile.getId());
		domain = _userProfileConverter.convertRemoteToDomain(domain, profile);
		saveOrUpdate(domain);
		return true;
	}

}