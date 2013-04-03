package com.box.boxjavalibv2.resourcemanagers;

import java.util.ArrayList;
import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CreateCollaborationRequest;
import com.box.boxjavalibv2.requests.DeleteCollaborationRequest;
import com.box.boxjavalibv2.requests.GetAllCollaborationsRequest;
import com.box.boxjavalibv2.requests.GetCollaborationRequest;
import com.box.boxjavalibv2.requests.UpdateCollaborationRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxCollabRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

/**
 * Use this class to execute requests <b>synchronously</b> against the Box REST API(V2), collaborations endpints. Full details about the Box API can be found at
 * <a href="http://developers.box.com/docs">http://developers.box.com/docs</a> . You must have an OpenBox application with a valid API key to use the Box API.
 * All methods in this class are executed in the invoking thread, and therefore are NOT safe to execute in the UI thread of your application. You should only
 * use this class if you already have worker threads or AsyncTasks that you want to incorporate the Box API into.
 */
public final class BoxCollaborationsManager extends BoxResourceManager {

    /**
     * Constructor.
     * 
     * @param config
     *            Config
     * @param resourceHub
     *            IResourceHub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxCollaborationsManager(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get a collaboration.
     * 
     * @param collabId
     *            id of the collaboration
     * @param requestObject
     *            object that goes into request.
     * @return collaboration (Errors may occur if the IDs are invalid or if the user does not have permissions to see the collaboration.)
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxCollaboration getCollaboration(final String collabId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        GetCollaborationRequest request = new GetCollaborationRequest(getConfig(), getObjectMapper(), collabId, requestObject);

        return (BoxCollaboration) getResponseAndParseAndTryCast(request, BoxResourceType.COLLABORATION, getObjectMapper());
    }

    /**
     * Add a collaboration for a single user to a folder.
     * 
     * @param folderId
     *            id of the folder
     * @param collabObject
     *            object that goes into request body.
     * @return collaboration
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxCollaboration createCollaboration(final String folderId, final BoxCollabRequestObject collabObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        CreateCollaborationRequest request = new CreateCollaborationRequest(getConfig(), getObjectMapper(), folderId, collabObject);

        return (BoxCollaboration) getResponseAndParseAndTryCast(request, BoxResourceType.COLLABORATION, getObjectMapper());
    }

    /**
     * Get all collaborations. (Currently only support getting all pending collaborations.)
     * 
     * @param collabObject
     *            object that goes into request.
     * @return collaborations
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public List<BoxCollaboration> getAllCollaborations(final BoxCollabRequestObject collabObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        GetAllCollaborationsRequest request = new GetAllCollaborationsRequest(getConfig(), getObjectMapper(), collabObject);

        BoxCollection collection = (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.COLLABORATIONS, getObjectMapper());
        return getCollaborations(collection);
    }

    /**
     * Delete a collaboration.
     * 
     * @param collabId
     *            id of the collaboration
     * @param collabObject
     *            object that goes into request.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public void deleteCollaboration(final String collabId, BoxDefaultRequestObject requestObject) throws BoxServerException, BoxRestException,
        AuthFatalFailureException {
        DeleteCollaborationRequest request = new DeleteCollaborationRequest(getConfig(), getObjectMapper(), collabId, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    /**
     * Update a collaboration.
     * 
     * @param collabId
     *            id of the collaboration
     * @param requestObject
     *            request object. Note the you can set the status in this object to ‘accepted’ or ‘rejected’ if you are the ‘accessible_by’ user and the current
     *            status is 'pending'
     * @return updated BoxCollaboration
     */
    public BoxCollaboration updateCollaboration(final String collabId, BoxCollabRequestObject requestObject) throws BoxRestException,
        AuthFatalFailureException, BoxServerException {
        UpdateCollaborationRequest request = new UpdateCollaborationRequest(getConfig(), getObjectMapper(), collabId, requestObject);
        return (BoxCollaboration) super.getResponseAndParseAndTryCast(request, BoxResourceType.COLLABORATION, getObjectMapper());
    }

    /**
     * Get collaborations from a collection.
     * 
     * @param collection
     *            collection
     * @return collaborations
     */
    public static List<BoxCollaboration> getCollaborations(BoxCollection collection) {
        List<BoxCollaboration> collabs = new ArrayList<BoxCollaboration>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxCollaboration) {
                collabs.add((BoxCollaboration) object);
            }
        }
        return collabs;
    }
}
