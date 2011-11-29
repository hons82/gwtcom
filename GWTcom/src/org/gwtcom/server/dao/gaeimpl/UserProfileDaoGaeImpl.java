package org.gwtcom.server.dao.gaeimpl;

import java.util.ArrayList;
import java.util.List;

import org.gwtcom.server.converter.gaeimpl.FriendsConverter;
import org.gwtcom.server.converter.gaeimpl.UserProfileConverter;
import org.gwtcom.server.dao.UserProfileDao;
import org.gwtcom.server.domain.UserLogin;
import org.gwtcom.server.domain.UserProfile;
import org.gwtcom.shared.FriendEntryRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@Repository("userProfileDao")
public class UserProfileDaoGaeImpl extends GenericDaoGaeImpl<UserProfile, String> implements UserProfileDao {

	@Autowired
	private UserProfileConverter _userProfileConverter;

	@Autowired
	private FriendsConverter _friendsConverter;

	public UserProfileDaoGaeImpl() {
		super(UserProfile.class);
	}

	@Override
	public UserProfileRemote getUserProfile(String userProfileId) {
		return _userProfileConverter.convertDomainToRemote(retrieve(userProfileId));
	}

	@Override
	public UserProfileRemote getUserProfile(UserLogin userLogin) {
		// TODO
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

	@Override
	@Transactional(readOnly = false)
	public UserProfile getProfileWithWall(String userProfileId) {
		UserProfile userProfile = retrieve(userProfileId);
		userProfile.getWall().size();
		return userProfile;
	}

	@Override
	@Transactional(readOnly = false)
	public List<FriendEntryRemote> getFriendsOfUser(UserLogin userLogin) {
		List<FriendEntryRemote> ret = new ArrayList<FriendEntryRemote>();

		for (Key key : getFriendsForUserLogin(userLogin)) {
			UserProfile item = _entityManager.find(UserProfile.class, key);
			ret.add(_friendsConverter.convertDomainToRemote(item));
		}
		return ret;
	}

	private List<Key> getFriendsForUserLogin(UserLogin userLogin) {
		UserProfile userProfile = userLogin.getUserprofile();
		List<Key> friends = userProfile.getFriends();
		friends.size();
		System.out.println(">>>>> Friends size: " + friends.size());
		return friends;
	}

	@Override
	@Transactional(readOnly = false)
	public void addFriendtoUser(String userProfileId, String friendID) {
		UserProfile userProfile = retrieve(userProfileId);
		if (userProfile != null) {
			userProfile.getFriends().add(KeyFactory.stringToKey(friendID));
			saveOrUpdate(userProfile);
		}
	}

	@Override
	public List<FriendEntryRemote> getPeople(String pattern) {
		List<FriendEntryRemote> ret = new ArrayList<FriendEntryRemote>();
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("UserProfile");

		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String firstName = (String) result.getProperty("firstName");
			String lastName = (String) result.getProperty("lastName");
			Long height = (Long) result.getProperty("height");
			System.out.println(lastName + " " + firstName + ", " + height.toString() + " inches tall");
		}
		return ret;
	}
}
