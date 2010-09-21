package org.gwtcom.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.gwtcom.client.service.ProfileService;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.UserLoginRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

	protected PersistenceManager persistenceManager;

	@Autowired
	public void setpersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	public ProfileServiceImpl() {
	}

	@Override
	public UserProfileRemote getProfile(Long id) {
		UserProfile item = persistenceManager.getObjectById(UserProfile.class, KeyFactory.createKey(UserProfile.class.getSimpleName(), id));
		if (item != null) {
			UserProfileRemote profile = new UserProfileRemote();
			profile.setId(item.getId().getId());
			profile.setName(item.getName());
			profile.setSurname(item.getSurname());
			profile.setEmail(item.getEmail());
			profile.setGender(item.getGender());
			return profile;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserLoginRemote getUserData(String name) {
		Query query = persistenceManager.newQuery("SELECT FROM " + UserLogin.class.getName() + " WHERE _name == nameParam");
		query.declareParameters("String nameParam");
		List<UserLogin> udl = (List<UserLogin>) query.execute(name);
		if (udl != null && udl.size() == 1) {
			UserLogin ud = udl.get(0);
			if (ud.getUserprofile() != null) {
				return new UserLoginRemote(ud.getUserprofile().getId().getId(), ud.getUserprofile().getName(), ud.getName());
			}
		}
		return null;
	}
}
