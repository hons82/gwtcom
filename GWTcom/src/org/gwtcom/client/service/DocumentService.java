package org.gwtcom.client.service;



import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface DocumentService extends RemoteService {

	public static final String SERVICE_URI = "documentService";

	public static class Util {
		public static DocumentServiceAsync getInstance() {
			DocumentServiceAsync instance = (DocumentServiceAsync) GWT
					.create(DocumentService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}
	
	/**
	 * Returns the number of public publications.
	 * 
	 * @return
	 */
	int getNumberOfPublicPublications();

	/**
	 * Returns the number of private publications.
	 * 
	 * @return
	 * @exception ServiceSecurityException
	 */
	int getNumberOfPrivatePublications() throws ServiceSecurityException;

}
