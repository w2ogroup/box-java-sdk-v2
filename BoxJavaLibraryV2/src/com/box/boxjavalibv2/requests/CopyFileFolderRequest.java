package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.utils.Utils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to copy a file/folder to a different parent folder.
 */
public class CopyFileFolderRequest extends DefaultBoxRequest {

    public static final String URI = "/%s/%s/copy";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param fileFolderId
     *            id of the file/folder to be copied
     * @param requestObject
     *            request object
     * @param isFolder
     *            whether it's a folder
     * @throws BoxRestException
     *             exception
     */
    public CopyFileFolderRequest(final IBoxConfig config, ObjectMapper objectMapper, final String fileFolderId, final BoxDefaultRequestObject requestObject,
        final boolean isFolder) throws BoxRestException {
        super(config, objectMapper, getUri(fileFolderId, isFolder), RestMethod.POST, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @param fileFolderId
     *            id of the file/folder to be copied
     * @param isFolder
     *            whether it is a folder
     * @return uri
     */
    public static String getUri(final String fileFolderId, final boolean isFolder) {
        return String.format(URI, Utils.getContainerString(isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE), fileFolderId);
    }
}
