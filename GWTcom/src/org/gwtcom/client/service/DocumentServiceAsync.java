package org.gwtcom.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Document service async interface.
 * 
 * @author See Wah Cheng
 * @created 5 Jun 2009
 */
public interface DocumentServiceAsync {

	public void getNumberOfPublicPublications(AsyncCallback<Integer> callback);

	public void getNumberOfPrivatePublications(AsyncCallback<Integer> callback);

}
