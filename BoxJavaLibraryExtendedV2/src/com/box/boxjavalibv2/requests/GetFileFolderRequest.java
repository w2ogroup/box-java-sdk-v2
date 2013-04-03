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
 * Request to get info of a file or folder.
 */
public class GetFileFolderRequest extends DefaultBoxRequest {

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
     * @param isFolder
     *            whether it is a folder
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             excepition
     */
    public GetFileFolderRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String fileFolderId, boolean isFolder,
        BoxDefaultRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(fileFolderId, isFolder), RestMethod.GET, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param fileFolderId
     *            id of the file/folder
     * @param isFolder
     *            whether it is a folder
     * @return uri
     */
    public static String getUri(final String fileFolderId, boolean isFolder) {
        return String.format(URI, Utils.getContainerString(isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE), fileFolderId);
    }
}
