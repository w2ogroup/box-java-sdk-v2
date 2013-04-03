package com.box.boxjavalibv2.exceptions;

import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.restclientv2.exceptions.BoxSDKException;

/**
 * Exception wrapping error responses received from api calls.
 */
public class BoxServerException extends BoxSDKException {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private BoxServerError error;
    private String customMessage;

    public BoxServerException() {
    }

    /**
     * Constructor.
     * 
     * @param customMessage
     *            message
     */
    public BoxServerException(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * Constructor.
     * 
     * @param result
     *            API response error.
     */
    public BoxServerException(BoxServerError error) {
        this.error = error;
    }

    /**
     * Get the API response error.
     * 
     * @return API response error.
     */
    public BoxServerError getError() {
        return error;
    }

    /**
     * Get the custom error message.
     * 
     * @return custom error message
     */
    public String getCustomMessage() {
        return customMessage;
    }

    @Override
    public int getStatusCode() {
        return error.getStatus();
    }
}
