package org.gwtcom.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.UserLogin;
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

	protected EntityManager entityManager;

	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private final String[] _roles = { "ROLE_ADMIN", "ROLE_USER" };
	private final String[][] _users = { { "hons", "hons" } };

	private final ShaPasswordEncoder _encoder;

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
				addProfiletoUser(_users[i][0]);
				System.out.println("Created User <" + _users[i][0] + "> with encoded Password: "
						+ _encoder.encodePassword(_users[i][1], null));
			}
		}
		//entityManager.flush();
		System.out.println(">>>>> Users initialized");

	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException, DataAccessException {
		// instantiates the basic users
		initUsers();

		System.out.println(">>>>> CustomUserDetailService.loadUserbyName(" + name + ")");
		UserLogin user = getUserbyName(name);
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
			Authority a = entityManager.find(Authority.class, k);
			authList.add(new GrantedAuthorityImpl(a.getAuthname()));
		}
		return authList;
	}

	private UserLogin getUserbyName(final String name) {

		Number count = (Number) entityManager.createQuery(
				"SELECT count(distinct _id) FROM " + UserLogin.class.getName() + " WHERE _name =\"" + name + "\"")
				.getSingleResult();
		if (count.intValue() == 1) {
			@SuppressWarnings("unchecked")
			List<UserLogin> resultList = entityManager.createQuery(
					"SELECT FROM " + UserLogin.class.getName() + " WHERE _name =\"" + name + "\"").getResultList();
			return resultList.get(0);
		}
		return null;
	}

	private Authority getAuthoritybyName(final String name) {
		Number count = (Number) entityManager
				.createQuery(
						"SELECT count(distinct _authname) FROM " + Authority.class.getName() + " WHERE _authname =\"" + name
								+ "\"").getSingleResult();
		if (count.intValue() == 1) {
			@SuppressWarnings("unchecked")
			List<Authority> resultList = entityManager.createQuery(
					"SELECT FROM " + Authority.class.getName() + " WHERE _authname =\"" + name + "\"").getResultList();
			return resultList.get(0);
		}
		return null;
	}

	private void createUser(String name, String password) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			UserLogin user = new UserLogin();
			user.setName(name);
			user.setPassword(encodePassword(password));
			entityManager.persist(user);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	private void addRoletoUser(String name, Authority auth) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			UserLogin user = getUserbyName(name);
			if (user != null) {
				user.getAuthorities().add(auth.getId());
				entityManager.persist(user);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	private void addProfiletoUser(String name) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			UserLogin user = getUserbyName(name);
			if (user != null) {
				UserProfile profile = new UserProfile("Johnny B.", "Good");
				profile.setEmail("somemail@aa.org");
				profile.setGender(UserProfile.GENDER_MALE);
				profile.setLogin(user);
				entityManager.persist(profile);

				user.setUserprofile(profile);
				entityManager.persist(user);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	private void createRole(String name) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			Authority auth = new Authority();
			auth.setAuthname(name);
			entityManager.persist(auth);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}

	public String encodePassword(String password) {
		return _encoder.encodePassword(password, null);
	}
}
