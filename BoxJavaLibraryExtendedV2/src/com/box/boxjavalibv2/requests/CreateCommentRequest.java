package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.requests.requestobjects.BoxCommentRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to add a comment to a file.
 */
public class CreateCommentRequest extends DefaultBoxRequest {

    private static final String URI = "/comments";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param requestObject
     *            comment request object.
     * @throws BoxRestException
     *             exception
     */
    public CreateCommentRequest(IBoxConfig config, final ObjectMapper objectMapper, BoxCommentRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(), RestMethod.POST, requestObject);
        setExpectedResponseCode(HttpStatus.SC_CREATED);
    }

    /**
     * Get uri.
     * 
     * @return uri
     */
    public static String getUri() {
        return URI;
    }
}
