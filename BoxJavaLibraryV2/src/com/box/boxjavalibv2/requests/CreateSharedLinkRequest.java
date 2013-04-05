package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.utils.Utils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to create a shared link for a file or folder.
 */
public class CreateSharedLinkRequest extends DefaultBoxRequest {

    private static final String URI = "/%s/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param fileFolderId
     *            id of the file/folder
     * @param requestObject
     *            request object
     * @param isFolder
     *            whether it is a folder
     * 
     * @throws BoxRestException
     *             exception
     */
    public CreateSharedLinkRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String fileFolderId, BoxDefaultRequestObject requestObject,
        final boolean isFolder) throws BoxRestException {
        super(config, objectMapper, getUri(fileFolderId, isFolder), RestMethod.PUT, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param fileFolderId
     *            id of this file/folder
     * @param isFolder
     *            whether it is folder
     * @return uri
     */
    public static String getUri(final String fileFolderId, final boolean isFolder) {
        return String.format(URI, Utils.getContainerString(isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE), fileFolderId);
    }
}
