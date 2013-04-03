package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxUserRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to adds a new email alias to the given user's account. This feature is currently only available to enterprise admins and the new email must be in a
 * domain associated with the enterprise and can not be a publicly atainable domain (e.g. gmail.com).
 */
public class CreateEmailAliasRequest extends DefaultBoxRequest {

    private static final String URI = "/users/%s/email_aliases";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param userId
     *            id of the user
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public CreateEmailAliasRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String userId, BoxUserRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(userId), RestMethod.POST, requestObject);
        setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @param userId
     *            id of user.
     * @return uri
     */
    public static String getUri(final String userId) {
        return String.format(URI, userId);
    }
}
