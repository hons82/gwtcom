package org.gwtcom.client.presenter;

public interface Display {
    /**
     * Indicate to the display that processing is being done.
     */
    void startProcessing();

    /**
     * Indicate to the display that processing has completed.
     */
    void stopProcessing();
}
