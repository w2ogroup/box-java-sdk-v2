package com.box.restclientv2.interfaces;

/**
 * Interface for API cookie.
 */
public interface ICookie {

    /**
     * Set cookie into API request.
     * 
     * @param request
     *            api request.
     */
    void setCookie(IBoxRequest request);
}
