package org.gwtcom.client.service;


import java.util.List;

import org.gwtcom.shared.UserLoginRemote;
import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtcom/profileService")
public interface ProfileService extends RemoteService {

	UserProfileRemote getProfile(Long item) throws ServiceSecurityException;

	UserLoginRemote getUserLogin(String name) throws ServiceSecurityException;
	
	public List<WallEntryRemote> getPublicWallEntries(Long id) throws ServiceSecurityException;
	
}
