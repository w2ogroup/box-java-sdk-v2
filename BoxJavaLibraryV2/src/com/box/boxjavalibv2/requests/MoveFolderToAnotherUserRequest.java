package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxUserRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Moves all of the content from within one user's folder into a new folder in another user's account. You can move folders across users as long as the you have
 * administrative permissions. To move everything from the root folder, use 0 which always represents the root folder of a Box account
 */
public class MoveFolderToAnotherUserRequest extends DefaultBoxRequest {

    private static final String URI = "/users/%s/folders/%s";

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param objectMapper
     *            object mapper
     * @param userId
     *            id of the signed in user
     * @param folderId
     *            id of the folder to be moved
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     */
    public MoveFolderToAnotherUserRequest(final IBoxConfig config, final ObjectMapper objectMapper, final String userId, final String folderId,
        final BoxUserRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(userId, folderId), RestMethod.PUT, requestObject);
    }

    /**
     * Get uri.
     * 
     * @param userId
     *            id of the user
     * @param folderId
     *            id of the folder
     * @return
     */
    public static String getUri(final String userId, final String folderId) {
        return String.format(URI, userId, folderId);
    }
}
