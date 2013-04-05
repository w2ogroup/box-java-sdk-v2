package com.box.boxjavalibv2.exceptions;

import com.box.restclientv2.exceptions.BoxSDKException;

/**
 * Exception indicating fatal error in authentication. May need to re-authenticate.
 */
public class AuthFatalFailureException extends BoxSDKException {

    private static final long serialVersionUID = 1L;
    private boolean callerResponsibleForFix;

    /**
     * Constructor.
     */
    public AuthFatalFailureException() {
        super();
    }

    public AuthFatalFailureException(boolean callerResponsibleForFix) {
        this();
        this.callerResponsibleForFix = callerResponsibleForFix;
    }

    /**
     * Constructor.
     */
    public AuthFatalFailureException(String message) {
        super(message);
    }

    /**
     * @return whether the caller is responsible to fix this issue.
     */
    public boolean isCallerResponsibleForFix() {
        return callerResponsibleForFix;
    }
}
