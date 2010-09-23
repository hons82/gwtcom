package org.gwtcom.client.service;


import org.gwtcom.shared.UserLoginRemote;
import org.gwtcom.shared.UserProfileRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtcom/profileService")
public interface ProfileService extends RemoteService {

	UserProfileRemote getProfile(Long item) throws ServiceSecurityException;

	UserLoginRemote getUserLogin(String name) throws ServiceSecurityException;
	
}
