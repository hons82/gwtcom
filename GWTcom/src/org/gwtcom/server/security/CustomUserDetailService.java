package org.gwtcom.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;

@Service("customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

	protected PersistenceManager persistenceManager;

	private final String[] _roles = { "ROLE_ADMIN", "ROLE_USER" };
	private final String[][] _users = { { "hons", "hons" }, { "anton", "anton" }, { "manni", "manni" }, { "omar", "omar" } };

	private ShaPasswordEncoder _encoder;

	public CustomUserDetailService() {
		_encoder = new ShaPasswordEncoder(256);
	}

	/**
	 * 
	 */
	private void initUsers() {
		// Initialize Roles
		for (int i = 0; i < _roles.length; i++) {
			if (getAuthoritybyName(_roles[i]) == null) {
				createRole(_roles[i]);
			}
		}

		// Initialize Users
		for (int i = 0; i < _users.length; i++) {
			if (getUserbyName(_users[i][0]) == null) {
				createUser(_users[i][0], _users[i][1]);
				addRoletoUser(_users[i][0], getAuthoritybyName(_roles[0]));
				addRoletoUser(_users[i][0], getAuthoritybyName(_roles[1]));
				System.out.println("Created User <" + _users[i][0] + "> with encoded Password: " + _encoder.encodePassword(_users[i][1], null));
			}
		}
		persistenceManager.flush();
		System.out.println(">>>>> Users initialized");

	}

	@Autowired
	public void setPersistenceManager(PersistenceManager entityManager) {
		this.persistenceManager = entityManager;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
		// instantiates the basic users
		initUsers();

		System.out.println(">>>>> CustomUserDetailService.loadUserbyName(" + name + ")");
		UserProfile user = getUserbyName(name);
		if (user == null) {
			throw new UsernameNotFoundException("User " + name + " not found!");
		}
		Collection<GrantedAuthority> auth = getAuthorities(user.getAuthorities());

		return new User(user.getName(), user.getPassword(), true, true, true, true, auth);
	}

	private Collection<GrantedAuthority> getAuthorities(Set<Key> auth) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (Iterator<Key> iterator = auth.iterator(); iterator.hasNext();) {
			Key k = iterator.next();
			Authority a = persistenceManager.getObjectById(Authority.class, k);
			authList.add(new GrantedAuthorityImpl(a.getAuthname()));
		}
		return authList;
	}

	private UserProfile getUserbyName(final String name) {

		Number count = (Number) persistenceManager.newQuery(
				"SELECT count(distinct _id) FROM " + UserProfile.class.getName() + " WHERE _name ==\"" + name + "\"").execute();
		if (count.intValue() == 1) {
			@SuppressWarnings("unchecked")
			List<UserProfile> resultList = (List<UserProfile>) persistenceManager.newQuery(
					"SELECT FROM " + UserProfile.class.getName() + " WHERE _name ==\"" + name + "\"").execute();
			return resultList.get(0);
		}
		return null;
	}

	private Authority getAuthoritybyName(final String name) {
		Number count = (Number) persistenceManager.newQuery(
				"SELECT count(distinct _authname) FROM " + Authority.class.getName() + " WHERE _authname ==\"" + name + "\"").execute();
		if (count.intValue() == 1) {
			@SuppressWarnings("unchecked")
			List<Authority> resultList = (List<Authority>) persistenceManager.newQuery(
					"SELECT FROM " + Authority.class.getName() + " WHERE _authname ==\"" + name + "\"").execute();
			return resultList.get(0);
		}
		return null;
	}

	private void createUser(String name, String password) {
		UserProfile user = new UserProfile();
		user.setName(name);
		user.setPassword(_encoder.encodePassword(password, null));
		persistenceManager.makePersistent(user);
	}

	private void addRoletoUser(String name, Authority auth) {
		Transaction tx = persistenceManager.currentTransaction();
		try {
			tx.begin();
			UserProfile user = getUserbyName(name);
			if (user != null) {
				user.getAuthorities().add(auth.getId());
				persistenceManager.makePersistent(user);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	private void createRole(String name) {
		Authority auth = new Authority();
		auth.setAuthname(name);
		persistenceManager.makePersistent(auth);
	}
}
