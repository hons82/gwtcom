package org.gwtcom.server.converter;

public interface IIdConverter<PK, S> {

	public S convertFromID(PK id);

	public PK convertToId(S encoded);
}
