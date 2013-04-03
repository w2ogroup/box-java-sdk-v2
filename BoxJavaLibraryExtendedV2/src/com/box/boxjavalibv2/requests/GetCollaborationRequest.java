package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get a collaboration. Can also request for collaborations of a certain status. Currently only
 * {@link com.box.boxjavalibv2.dao.CollaborationV2.STATUS_PENDING} is allowed.
 */
public class GetCollaborationRequest extends DefaultBoxRequest {

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
    public GetCollaborationRequest(final IBoxConfig config, final ObjectMapper objectMapper, String collabId, final BoxDefaultRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(collabId), RestMethod.GET, requestObject);
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
