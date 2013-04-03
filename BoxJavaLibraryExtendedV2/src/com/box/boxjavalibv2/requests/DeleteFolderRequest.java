package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxFolderRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to delete a folder.
 */
public class DeleteFolderRequest extends DefaultBoxRequest {

    private static final String URI = "/folders/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param folderId
     *            id of the folder to be deleted
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public DeleteFolderRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String folderId, final BoxFolderRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(folderId), RestMethod.DELETE, requestObject);
        setExpectedResponseCode(HttpStatus.SC_NO_CONTENT);
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
