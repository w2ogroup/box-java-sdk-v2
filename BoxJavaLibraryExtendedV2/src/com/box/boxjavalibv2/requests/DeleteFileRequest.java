package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxFileRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to delete a file.
 */
public class DeleteFileRequest extends DefaultBoxRequest {

    private static final String URI = "/files/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param fileId
     *            id of the file
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public DeleteFileRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String fileId, BoxFileRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(fileId), RestMethod.DELETE, requestObject);
        setExpectedResponseCode(HttpStatus.SC_NO_CONTENT);
    }

    /**
     * Get uri.
     * 
     * @param fileId
     *            id of the file
     * @return uri
     */
    public static String getUri(final String fileId) {
        return String.format(URI, fileId);
    }
}
