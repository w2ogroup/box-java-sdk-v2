package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxCollabRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get all collaborations. (Currently only support getting all pending collaborations.)
 */
public class GetAllCollaborationsRequest extends DefaultBoxRequest {

    private static final String URI = "/collaborations";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param status
     *            status of the collaborations requested( This field is required and currently only support
     *            {@link com.box.boxjavalibv2.dao.CollaborationV2.STATUS_PENDING}
     * @throws BoxRestException
     *             exception
     */
    public GetAllCollaborationsRequest(final IBoxConfig config, final ObjectMapper objectMapper, BoxCollabRequestObject collabObject) throws BoxRestException {
        super(config, objectMapper, getUri(), RestMethod.GET, collabObject);
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
