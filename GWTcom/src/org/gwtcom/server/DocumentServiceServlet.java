package org.gwtcom.server;

import org.gwtcom.client.service.DocumentService;
import org.gwtcom.client.service.ServiceSecurityException;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
public class DocumentServiceServlet extends DependencyInjectionRemoteServiceServlet implements DocumentService {

	@Autowired
	DocumentService documentService;

	public int getNumberOfPrivatePublications() throws ServiceSecurityException {
		try {
			return documentService.getNumberOfPrivatePublications();
		} catch (Throwable e) {
			throw new ServiceSecurityException();
		}
	}

	public int getNumberOfPublicPublications() {
		return documentService.getNumberOfPublicPublications();
	}

}
