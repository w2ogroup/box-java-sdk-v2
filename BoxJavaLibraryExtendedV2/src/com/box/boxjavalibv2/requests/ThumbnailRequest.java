package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxImageRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to get thumbnail.
 */
public class ThumbnailRequest extends DefaultBoxRequest {

    private final static String URI = "/files/%s/thumbnail.%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param fileId
     *            id of the file
     * @param fileExtension
     *            extension of the thumbnail file to be fetched
     * @param requstObject
     *            request object
     * @throws BoxRestException
     */
    public ThumbnailRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String fileId, final String fileExtension,
        final BoxImageRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(fileId, fileExtension), RestMethod.GET, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param fileId
     *            id of the file
     * @param fileExtension
     *            extension of the requested preview
     * @return uri
     */
    public static String getUri(String fileId, String fileExtension) {
        return String.format(URI, fileId, fileExtension);
    }
}
