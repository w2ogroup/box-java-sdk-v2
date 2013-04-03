package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteCollaborationRequest extends DefaultBoxRequest {

    private static final String URI = "/collaborations/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param collabId
     *            id of the collaboration
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public DeleteCollaborationRequest(final IBoxConfig config, final ObjectMapper objectMapper, String collabId, BoxDefaultRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(collabId), RestMethod.DELETE, requestObject);
        setExpectedResponseCode(HttpStatus.SC_NO_CONTENT);
    }

    /**
     * Get uri.
     * 
     * @param collabId
     *            collaboration id
     * @return uri
     */
    public static String getUri(final String collabId) {
        return String.format(URI, collabId);
    }
}
