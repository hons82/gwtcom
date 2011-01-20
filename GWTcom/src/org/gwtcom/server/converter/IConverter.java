package org.gwtcom.server.converter;

import org.gwtcom.server.domain.BaseDomainObject;
import org.gwtcom.shared.BaseDomainRemote;

public interface IConverter<R extends BaseDomainRemote, D extends BaseDomainObject> {

	public R convertDomainToRemote(D domain);

	public D convertRemoteToDomain(R remote);
}
