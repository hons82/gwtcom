package org.gwtcom.client.service;


import java.util.List;

import org.gwtcom.shared.UserProfileRemote;
import org.gwtcom.shared.WallEntryRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtcom/profileService")
public interface ProfileService extends RemoteService {

	public UserProfileRemote getUserProfile(Long userProfileId) throws ServiceSecurityException;

	public UserProfileRemote getUserProfileByUserLoginId(Long userLoginId);
	
	public List<WallEntryRemote> getPublicWallEntries(Long userProfileId) throws ServiceSecurityException;

	public WallEntryRemote addWallPost(Long userProfileId, String content);

}
