package com.box.boxjavalibv2.exceptions;

import com.box.boxjavalibv2.dao.BoxServerError;


/**
 * Unexpected http status code(not error status code).
 */
public class BoxUnexpectedStatus extends BoxServerError {

    public BoxUnexpectedStatus(int status) {
        setStatus(status);
    }
}
