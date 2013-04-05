package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxUserRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request used to provision a new user in an enterprise. This method only works for enterprise admins.
 */
public class CreateEnterpriseUserRequest extends DefaultBoxRequest {

    private static final String URI = "/users";

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
     *             exception
     */
    public CreateEnterpriseUserRequest(final IBoxConfig config, final ObjectMapper objectMapper, BoxUserRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(), RestMethod.POST, requestObject);
        setExpectedResponseCode(HttpStatus.SC_CREATED);
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
