package org.gwtcom.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;
import org.gwtcom.server.domain.Authority;
import org.gwtcom.server.domain.ProfileImage;
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

	private static final String IMAGE_B64 = "data:image/png;base64,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";
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
				
				ProfileImage profileImage = new ProfileImage();
				//profileImage.setPicture(Base64Utils.fromBase64(IMAGE_B64));
				entityManager.persist(profileImage);
				
				profile.setProfileImage(profileImage);
				entityManager.persist(profile);

				user.setUserprofile(profile);
				entityManager.persist(user);
			}
			tx.commit();
		} catch (Exception e) {
			Logger.getLogger("datastore").error("Rollback: "+e.getLocalizedMessage());
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
