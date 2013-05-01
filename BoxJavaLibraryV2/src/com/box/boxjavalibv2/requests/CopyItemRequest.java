package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to copy a file/folder to a different parent folder.
 */
public class CopyItemRequest extends DefaultBoxRequest {

    public static final String URI = "/%s/%s/copy";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param id
     *            id of the item to be copied
     * @param requestObject
     *            request object
     * @param type
     *            resource type of the item
     * @throws BoxRestException
     *             exception
     */
    public CopyItemRequest(final IBoxConfig config, ObjectMapper objectMapper, final String id, final BoxDefaultRequestObject requestObject,
        final BoxResourceType type) throws BoxRestException {
        super(config, objectMapper, getUri(id, type), RestMethod.POST, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @param id
     *            id of the item to be copied
     * @param type
     *            whether it is a folder
     * @return uri
     */
    public static String getUri(final String id, final BoxResourceType type) {
        return String.format(URI, type.toPluralString(), id);
    }
}
