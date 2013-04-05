package com.box.boxjavalibv2.resourcemanagers;

import java.io.UnsupportedEncodingException;

import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CopyFileFolderRequest;
import com.box.boxjavalibv2.requests.CreateSharedLinkRequest;
import com.box.boxjavalibv2.requests.GetFileFolderRequest;
import com.box.boxjavalibv2.requests.UpdateFileFolderInfoRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

/**
 * Use this class to execute requests <b>synchronously</b> against the Box REST API(V2). Full details about the Box API can be found at {@see <a
 * href="http://developers.box.com/docs">http://developers.box.com/docs</a>} . You must have an OpenBox application with a valid API key to use the Box API. All
 * methods in this class are executed in the invoking thread, and therefore are NOT safe to execute in the UI thread of your application. You should only use
 * this class if you already have worker threads or AsyncTasks that you want to incorporate the Box API into.
 */
public class BoxFileFoldersManager extends BoxResourceManager {

    /**
     * Constructor.
     * 
     * @param config
     *            BoxConfig
     * @param resourceHub
     *            IResourceHub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxFileFoldersManager(IBoxConfig config, IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get file/folder given a file/folder id.
     * 
     * @param fileFolderId
     *            id of the file/folder
     * @param BoxBasicRequestObject
     *            requestObject
     * @param isFolder
     *            whether it's a folder.
     * @return requested box file/folder
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxItem getFileOrFolder(final String fileFolderId, BoxDefaultRequestObject requestObject, final boolean isFolder) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        GetFileFolderRequest request = new GetFileFolderRequest(getConfig(), getObjectMapper(), fileFolderId, isFolder, requestObject);
        Object result = getResponseAndParse(request, isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE, getObjectMapper());
        return (BoxItem) tryCastBoxItem(isFolder, result);
    }

    /**
     * Copy a file or folder.
     * 
     * @param fileFolderId
     *            id of the file/folder
     * @param requestObject
     *            request object
     * @param isFolder
     *            whether it's a folder.
     * @return copied file/folder
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxItem copyFileFolder(final String fileFolderId, BoxItemRequestObject requestObject, final boolean isFolder) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        CopyFileFolderRequest request = new CopyFileFolderRequest(getConfig(), getObjectMapper(), fileFolderId, requestObject, isFolder);
        return (BoxItem) getResponseAndParseAndTryCast(request, isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE, getObjectMapper());
    }

    /**
     * Update info for a file/folder.
     * 
     * @param fileFolderId
     *            id of the file/folder
     * @param requestObject
     *            request object
     * @param isFolder
     *            whether it's a folder
     * @return updated file/folder
     * @throws UnsupportedEncodingException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxItem updateFileFolderInfo(final String fileFolderId, BoxItemRequestObject requestObject, final boolean isFolder)
        throws UnsupportedEncodingException, BoxRestException, BoxServerException, AuthFatalFailureException {
        UpdateFileFolderInfoRequest request = new UpdateFileFolderInfoRequest(getConfig(), getObjectMapper(), fileFolderId, requestObject, isFolder);
        return (BoxItem) getResponseAndParseAndTryCast(request, isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE, getObjectMapper());
    }

    /**
     * Create a shared link for a file/folder, given the id of the file/folder.
     * 
     * @param auth
     *            authorization
     * @param fileFolderId
     *            id of the file/folder
     * @param requestObject
     *            request object
     * @param isFolder
     *            whether this is a folder.
     * @return the file/folder, with shared link related fields filled in.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxItem createSharedLink(final String fileFolderId, BoxItemRequestObject requestObject, final boolean isFolder) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        CreateSharedLinkRequest request = new CreateSharedLinkRequest(getConfig(), getObjectMapper(), fileFolderId, requestObject, isFolder);

        return (BoxItem) getResponseAndParseAndTryCast(request, isFolder ? BoxResourceType.FOLDER : BoxResourceType.FILE, getObjectMapper());
    }
}
