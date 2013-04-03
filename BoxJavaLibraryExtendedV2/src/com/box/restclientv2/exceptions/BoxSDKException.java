package com.box.restclientv2.exceptions;

public abstract class BoxSDKException extends Exception {

    public BoxSDKException(String message) {
        super(message);
    }

    public BoxSDKException(Exception exception) {
        super(exception);
    }

    public BoxSDKException() {
        super();
    }

    /**
     * Status code of this exception. This could be helpful for exception wrapping http responses. By default, it is -1.
     */
    public int getStatusCode() {
        return -1;
    }

    private static final long serialVersionUID = 1L;

}
