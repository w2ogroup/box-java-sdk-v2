package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxEventRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get events. http://developers.box.com/docs/#events
 */
public class GetEventsRequest extends DefaultBoxRequest {

    private static final String URI = "/events";

    /**
     * Constructor.
     * 
     * @param config
     * @param objectMapper
     * @param requestObject
     * @throws BoxEventRequestObject
     */
    public GetEventsRequest(final IBoxConfig config, final ObjectMapper objectMapper, BoxEventRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, URI, RestMethod.GET, requestObject);
    }

}
