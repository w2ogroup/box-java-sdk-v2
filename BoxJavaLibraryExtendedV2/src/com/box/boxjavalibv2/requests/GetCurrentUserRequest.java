package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get the current user's information.
 */
public class GetCurrentUserRequest extends DefaultBoxRequest {

    private static final String URI = "/users/me";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param requestObject
     *            request object
     * @throws BoxRestException
     */
    public GetCurrentUserRequest(IBoxConfig config, final ObjectMapper objectMapper, BoxDefaultRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(), RestMethod.GET, requestObject);
    }

    /**
     * Get uri.
     * 
     * @return uri
     */
    public static String getUri() {
        return URI;
    }
}
