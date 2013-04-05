package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to download a file.
 */
public class DownloadFileRequest extends DefaultBoxRequest {

    private static final String URI = "/files/%s/content";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param fileId
     *            id of the file to be downloaded
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public DownloadFileRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String fileId, BoxDefaultRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(fileId), RestMethod.GET, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_OK);
    }

    /**
     * Get uri.
     * 
     * @param fileId
     *            id of file
     * @return uri
     */
    public static String getUri(final String fileId) {
        return String.format(URI, fileId);
    }

    @Override
    public String getScheme() {
        return getConfig().getDownloadUrlScheme();
    }

    @Override
    public String getAuthority() {
        return getConfig().getDownloadUrlAuthority();
    }
}
