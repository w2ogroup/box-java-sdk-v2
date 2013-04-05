package com.box.boxjavalibv2.resourcemanagers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CreateNewFolderRequest;
import com.box.boxjavalibv2.requests.DeleteFolderRequest;
import com.box.boxjavalibv2.requests.GetFolderCollaborationsRequest;
import com.box.boxjavalibv2.requests.GetFolderItemsRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxFolderRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public class BoxFoldersManager extends BoxFileFoldersManager {

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
    public BoxFoldersManager(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get folder given a folder id.
     * 
     * @param folderId
     *            id of the folder
     * @param requestObject
     *            object that goes into request.
     * @return requested box folder
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFolder getFolder(final String folderId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFolder) super.getFileOrFolder(folderId, requestObject, true);
    }

    /**
     * Create a folder.
     * 
     * @param requestObject
     *            request object
     * @return created folder
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFolder createFolder(BoxFolderRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        CreateNewFolderRequest request = new CreateNewFolderRequest(getConfig(), getObjectMapper(), requestObject);
        return (BoxFolder) getResponseAndParseAndTryCast(request, BoxResourceType.FOLDER, getObjectMapper());
    }

    /**
     * Delete a folder.
     * 
     * @param folderId
     *            id of the folder
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public void deleteFolder(final String folderId, BoxFolderRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        DeleteFolderRequest request = new DeleteFolderRequest(getConfig(), getObjectMapper(), folderId, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    /**
     * Copy a folder.
     * 
     * @param folderId
     *            id of the folder
     * @param requestObject
     *            request object
     * @return copied folder
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxFolder copyFolder(final String folderId, BoxFolderRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFolder) super.copyFileFolder(folderId, requestObject, true);
    }

    /**
     * Get the items(subfolders, files, weblinks...) under a folder. By default, returning maximum of {@link GetFolderItemsRequest#DEFAULT_FOLDER_ITEMS_LIMIT}
     * items, at an offset of {@link GetFolderItemsRequest#DEFAULT_FOLDER_ITEMS_OFFSET}
     * 
     * @param folderId
     *            id of the folder.
     * @param requestObject
     *            request object
     * @return Items(subfolders, files, weblinks...) under the folder.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxCollection getFolderItems(final String folderId, BoxFolderRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        GetFolderItemsRequest request = new GetFolderItemsRequest(getConfig(), getObjectMapper(), folderId, requestObject);
        return getFolderItems(request, getObjectMapper());
    }

    /**
     * Update info for a folder.
     * 
     * @param fileFolderId
     *            id of the folder
     * @param requestObject
     *            request object
     * @return updated folder
     * @throws UnsupportedEncodingException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFolder updateFolderInfo(final String folderId, final BoxFolderRequestObject requestObject) throws UnsupportedEncodingException, BoxRestException,
        BoxServerException, AuthFatalFailureException {
        return (BoxFolder) super.updateFileFolderInfo(folderId, requestObject, true);
    }

    /**
     * Create a shared link for a folder, given the id of the file/folder.
     * 
     * @param fileFolderId
     *            id of the folder
     * @param isFolder
     *            whether this is a folder.
     * @param perm
     *            SharedLinkPermissions
     * @param access
     *            SharedLinkAccess, String can be the strings defined in {@link com.box.boxjavalibv2.dao.BoxSharedLinkAccess}.
     * @param unsharedAt
     *            the time the created shared link expires.
     * @return the file/folder, with shared link related fields filled in.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFolder createSharedLink(final String folderId, BoxFolderRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFolder) super.createSharedLink(folderId, requestObject, true);
    }

    /**
     * Get collaborations of a folder.
     * 
     * @param folderId
     *            id of the folder
     * @return collaborations
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public List<BoxCollaboration> getFolderCollaborations(final String folderId, BoxDefaultRequestObject requestObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        GetFolderCollaborationsRequest request = new GetFolderCollaborationsRequest(getConfig(), getObjectMapper(), folderId, requestObject);

        BoxCollection collection = (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.COLLABORATIONS, getObjectMapper());
        return BoxCollaborationsManager.getCollaborations(collection);
    }

    /**
     * Get folders in a collection.
     * 
     * @param collection
     *            collection
     * @return list of folders
     */
    public static List<BoxFolder> getFolders(BoxCollection collection) {
        List<BoxFolder> folders = new ArrayList<BoxFolder>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxFolder) {
                folders.add((BoxFolder) object);
            }
        }
        return folders;
    }
}
