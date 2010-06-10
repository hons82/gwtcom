	package org.gwtcom.server;

import org.gwtcom.client.service.DocumentService;
import org.springframework.security.access.annotation.Secured;

public class DocumentServiceImpl implements DocumentService {

	private int privatePublicationCount;

	private int publicPublicationCount;

	public DocumentServiceImpl() {
		setPrivatePublicationCount(666);
		setPublicPublicationCount(1000);
	}

	public void setPrivatePublicationCount(int privatePublicationCount) {
		this.privatePublicationCount = privatePublicationCount;
	}

	public void setPublicPublicationCount(int publicPublicationCount) {
		this.publicPublicationCount = publicPublicationCount;
	}

	@Secured("ROLE_ADMIN")
	public int getNumberOfPrivatePublications() {
		return privatePublicationCount;
	}

	public int getNumberOfPublicPublications() {
		return publicPublicationCount;
	}

}
