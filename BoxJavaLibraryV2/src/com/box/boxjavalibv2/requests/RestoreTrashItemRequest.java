package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRestoreRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestoreTrashItemRequest extends DefaultBoxRequest {

    private static final String URI = "/%s/%s";

    /**
     * Cosntructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param id
     *            id of the item
     * @param type
     *            resource type of the item
     * @param requestObject
     *            request object
     * @throws BoxRestException
     */
    public RestoreTrashItemRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String id, final BoxResourceType type,
        final BoxItemRestoreRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(id, type), RestMethod.POST, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @param id
     *            id of the item
     * @param itemType
     *            type of the item
     * @return uri
     */
    public static String getUri(final String id, final BoxResourceType type) {
        return String.format(URI, type.toPluralString(), id);
    }
}
