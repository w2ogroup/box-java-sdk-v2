package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxFolderRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get the trashed items inside a folder. These items can be files, sub-folders, weblinks, and etc.
 */
public class GetFolderTrashItemsRequest extends DefaultBoxRequest {

    private static final String URI = "/folders/%s/trash/items";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param folderId
     *            id of the folder
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public GetFolderTrashItemsRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String folderId, BoxFolderRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(folderId), RestMethod.GET, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param folderId
     *            id of the folder
     * @return uri
     */
    public static String getUri(final String folderId) {
        return String.format(URI, folderId);
    }
}
