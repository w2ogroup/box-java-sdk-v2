package com.box.boxjavalibv2.requests;

import java.io.UnsupportedEncodingException;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.utils.Utils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to update info of a file or folder.
 */
public class UpdateItemInfoRequest extends DefaultBoxRequest {

    private static final String URI = "/%s/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param id
     *            id of the item
     * @param requestObject
     *            request object
     * @param type
     *            resource type of the objects
     * @throws BoxRestException
     * @throws UnsupportedEncodingException
     */
    public UpdateItemInfoRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String fileFolderId,
        final BoxDefaultRequestObject requestObject, final BoxResourceType type) throws BoxRestException, UnsupportedEncodingException {
        super(config, objectMapper, getUri(fileFolderId, type), RestMethod.PUT, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param fileFolderId
     *            id of the file/folder
     * @param type
     *            whether it is a folder
     * @return uri
     */
    public static String getUri(final String fileFolderId, final BoxResourceType type) {
        return String.format(URI, type.toPluralString(), fileFolderId);
    }
}
