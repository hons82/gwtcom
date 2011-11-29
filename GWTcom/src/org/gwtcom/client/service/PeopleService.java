package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.FriendEntryRemote;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service/peopleService")
public interface PeopleService extends RemoteService {

	public List<FriendEntryRemote> getPeople(String pattern) throws ServiceSecurityException;

}
