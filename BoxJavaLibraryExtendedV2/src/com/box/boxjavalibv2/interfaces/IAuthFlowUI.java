package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.BoxClient;

/**
 * Interface for UI to generate auth.
 */
public interface IAuthFlowUI {

    /**
     * Initialize the UI for OAuth flow. This needs to be called everytime before starting an auth flow.
     * 
     * @param boxClient
     *            BoxClient
     * @param applicationContext
     *            application specific context.
     */
    void initializeAuthFlow(BoxClient boxClient, final Object applicationContext);

    /**
     * Authenticate.
     * 
     * @param listener
     *            listener listening to events/messages fired during authentication process.
     */
    void authenticate(IAuthFlowListener listener);

}
