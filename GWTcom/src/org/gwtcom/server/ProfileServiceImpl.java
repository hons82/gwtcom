package org.gwtcom.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.gwtcom.client.service.ProfileService;
import org.gwtcom.server.converter.UserProfileConverter;
import org.gwtcom.server.converter.WallEntryConverter;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service("profileService")
public class ProfileServiceImpl extends AbstractDatabaseService implements ProfileService {

	private WallEntryConverter _wallEntryConverter;
	private UserProfileConverter _userProfileConverter;

	@Autowired
	public void setWallEntryConverter(WallEntryConverter wallEntryConverter) {
		_wallEntryConverter = wallEntryConverter;
	}

	@Autowired
	public void setUserProfileConverter(UserProfileConverter userProfileConverter) {
		_userProfileConverter = userProfileConverter;
	}

	public ProfileServiceImpl() {
	}

	@Override
	public UserProfileRemote getUserProfile(Long userProfileId) {
		return _userProfileConverter.convertDomainToRemote(_entityManager.find(UserProfile.class,
				KeyFactory.createKey(UserProfile.class.getSimpleName(), userProfileId)));
	}

	@Override
	public UserProfileRemote getUserProfileByUserLoginId(Long userLoginId) {
		UserLogin userLogin = getUserLogin(userLoginId);
		return _userProfileConverter.convertDomainToRemote(userLogin != null ? userLogin.getUserprofile() : null);
	}

	@Override
	public List<WallEntryRemote> getPublicWallEntries(Long userLoginId) {
		List<WallEntryRemote> ret = new ArrayList<WallEntryRemote>();

		UserLogin login = getUserLogin(userLoginId);

		for (Key key : login.getUserprofile().getWall()) {
			WallEntry item = _entityManager.find(WallEntry.class, key);
			ret.add(_wallEntryConverter.convertDomainToRemote(item));
		}
		return ret;
	}

	private UserLogin getUserLogin(Long userLoginId) {
		return _entityManager.find(UserLogin.class, KeyFactory.createKey(UserLogin.class.getSimpleName(), userLoginId));
	}

	private UserProfile getUserProfileById(Long userProfileId) {
		return _entityManager.find(UserProfile.class,
				KeyFactory.createKey(UserLogin.class.getSimpleName(), userProfileId));
	}

	@Override
	public WallEntryRemote addWallPost(Long userLoginId, String content) {
		UserLogin login = getUserLogin(userLoginId);
		UserProfile profile = login.getUserprofile();
		List<Key> wall = profile.getWall();

		WallEntry wallEntry = new WallEntry();
		if (login != null && profile != null) {
			EntityTransaction tx = _entityManager.getTransaction();
			try {
				tx.begin();

				wallEntry.setOwner(profile.getId());
				wallEntry.setDateAdded(new Date());
				wallEntry.setContent(content);
				_entityManager.persist(wallEntry);

				tx.commit();
			} catch (Exception e) {
				if (tx.isActive())
					tx.rollback();
			}
			//
			try {
				tx.begin();
				wall.add(wallEntry.getId());
				profile.setWall(wall);
				_entityManager.merge(profile);
				tx.commit();
			} catch (Exception e) {
				if (tx.isActive())
					tx.rollback();
			}
		}
		return _wallEntryConverter.convertDomainToRemote(wallEntry);
	}

}
