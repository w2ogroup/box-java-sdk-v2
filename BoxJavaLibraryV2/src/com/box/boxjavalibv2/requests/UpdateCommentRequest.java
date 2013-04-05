package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxCommentRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request to update a comment.
 */
public class UpdateCommentRequest extends DefaultBoxRequest {

    private static String URI = "/comments/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param commentId
     *            id of the comment
     * @param requestObject
     *            comment request object.
     * @throws BoxRestException
     *             exception
     */
    public UpdateCommentRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String commentId, final BoxCommentRequestObject requestObject)
        throws BoxRestException {
        super(config, objectMapper, getUri(commentId), RestMethod.PUT, requestObject);
    }

    public static String getUri(final String commentId) {
        return String.format(URI, commentId);
    }
}
