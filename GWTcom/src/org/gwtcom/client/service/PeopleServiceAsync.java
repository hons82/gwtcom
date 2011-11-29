package org.gwtcom.client.service;

import java.util.List;

import org.gwtcom.shared.FriendEntryRemote;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PeopleServiceAsync {

	public void getPeople(String pattern, AsyncCallback<List<FriendEntryRemote>> callback);

}
