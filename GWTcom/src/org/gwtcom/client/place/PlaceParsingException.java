package org.gwtcom.client.place;

public class PlaceParsingException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1769549308655071562L;

	public PlaceParsingException() {
    }

    public PlaceParsingException( String message ) {
        super( message );
    }

    public PlaceParsingException( Throwable cause ) {
        super( cause );
    }

    public PlaceParsingException( String message, Throwable cause ) {
        super( message, cause );
    }

}
