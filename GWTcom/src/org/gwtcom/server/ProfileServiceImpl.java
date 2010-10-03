package org.gwtcom.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.gwtcom.client.service.ProfileService;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.server.domain.WallEntry;
import org.gwtcom.shared.UserLoginRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;
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
		UserLogin item = persistenceManager.getObjectById(UserLogin.class, KeyFactory.createKey(UserLogin.class.getSimpleName(), id));
		if (item != null) {
			UserProfileRemote profile = new UserProfileRemote();
			profile.setId(id);
			profile.setName(item.getUserprofile().getName());
			profile.setSurname(item.getUserprofile().getSurname());
			profile.setEmail(item.getUserprofile().getEmail());
			profile.setGender(item.getUserprofile().getGender());
			return profile;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserLoginRemote getUserLogin(String name) {
		Query query = persistenceManager.newQuery("SELECT FROM " + UserLogin.class.getName() + " WHERE _name == nameParam");
		query.declareParameters("String nameParam");
		List<UserLogin> udl = (List<UserLogin>) query.execute(name);
		if (udl != null && udl.size() == 1) {
			UserLogin ud = udl.get(0);
			if (ud.getUserprofile() != null) {
				return new UserLoginRemote(ud.getId().getId(), ud.getUserprofile().getName(), ud.getName());
			}
		}
		return null;
	}
	
	@Override
	public List<WallEntryRemote> getPublicWallEntries(Long id) {
		List<WallEntryRemote> ret = new ArrayList<WallEntryRemote>();
		
		UserLogin login = persistenceManager.getObjectById(UserLogin.class, KeyFactory.createKey(UserLogin.class.getSimpleName(), id));
		
		for (WallEntry item : login.getUserprofile().getWall()) {
			ret.add(new WallEntryRemote(item.getId().getId(), item.getDateAdded(), ProfileServiceImpl.serializeUserProfile(item.getOwner()), ProfileServiceImpl.serializeUserProfile(item.getAuthor()), item.getContent()));
		}
		return ret;
	}
	
	public static UserProfileRemote serializeUserProfile(UserProfile user) {
		UserProfileRemote remote = new UserProfileRemote();
		if (user != null) {
			remote.setId(user.getId().getId());
			remote.setName(user.getName());
			remote.setSurname(user.getSurname());
			remote.setEmail(user.getEmail());
			remote.setGender(user.getGender());
		}
		return remote;
	}
}
