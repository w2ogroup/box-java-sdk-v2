package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get the items inside a folder. These items can be files, sub-folders, weblinks, and etc.
 */
public class GetEventsRequest extends DefaultBoxRequest {

    private static final String URI = "/events";

    public static final String STREAM_TYPE_ALL = "all";
    public static final String STREAM_TYPE_CHANGES = "changes";
    public static final String STREAM_TYPE_SYNC = "sync";

    public static final int STREAM_POSITION_NOW = -1;

    /**
     * Constructor.
     * 
     * @param config
     * @param objectMapper
     * @param stream_position
     * @param stream_type
     * @param limit
     * @param requestObject
     * @throws BoxRestException
     */
    public GetEventsRequest(final IBoxConfig config, final ObjectMapper objectMapper, final long stream_position, final String stream_type, final int limit,
        BoxDefaultRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, URI, RestMethod.GET, requestObject);
        if (stream_position == STREAM_POSITION_NOW) {
            addQueryParam("stream_position", "now");
        }
        else {
            addQueryParam("stream_position", String.valueOf(stream_position));
        }
        addQueryParam("stream_type", stream_type);
        addQueryParam("limit", String.valueOf(limit));
    }

}
