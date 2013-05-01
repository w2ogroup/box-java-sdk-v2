package com.box.boxjavalibv2.resourcemanagers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFileVersion;
import com.box.boxjavalibv2.dao.BoxPreview;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.filetransfer.BoxFileDownload;
import com.box.boxjavalibv2.filetransfer.BoxFileUpload;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.interfaces.IFileTransferListener;
import com.box.boxjavalibv2.requests.DeleteFileRequest;
import com.box.boxjavalibv2.requests.GetFileCommentsRequest;
import com.box.boxjavalibv2.requests.GetFileVersionsRequest;
import com.box.boxjavalibv2.requests.PreviewRequest;
import com.box.boxjavalibv2.requests.ThumbnailRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileUploadRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxImageRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxItemRestoreRequestObject;
import com.box.boxjavalibv2.responseparsers.ErrorResponseParser;
import com.box.boxjavalibv2.responseparsers.PreviewResponseParser;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;
import com.box.restclientv2.responseparsers.DefaultFileResponseParser;
import com.box.restclientv2.responses.DefaultBoxResponse;

public class BoxFilesManager extends BoxItemsManager {

    /**
     * Constructor.
     * 
     * @param config
     *            BoxConfig
     * @param resourceHub
     *            resource hub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxFilesManager(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get file given a file id.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            object that goes into request.
     * @return requested box file
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFile getFile(final String fileId, final BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFile) super.getItem(fileId, requestObject, BoxResourceType.FILE);
    }

    /**
     * Get trashed file given a file id.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            object that goes into request.
     * @return requested box file
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFile getTrashFile(final String fileId, final BoxDefaultRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        return (BoxFile) super.getTrashItem(fileId, BoxResourceType.FILE, requestObject);
    }

    /**
     * Delete a file.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            object that goes into request.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public void deleteFile(final String fileId, final BoxFileRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        DeleteFileRequest request = new DeleteFileRequest(getConfig(), getObjectMapper(), fileId, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    /**
     * Permanently delete a trashed file.
     * 
     * @param id
     *            id of the file
     * @param requestObject
     *            request object
     * @throws BoxRestException
     * @throws AuthFatalFailureException
     * @throws BoxServerException
     */
    public void deleteTrashFile(final String id, final BoxFileRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        super.deleteTrashItem(id, BoxResourceType.FILE, requestObject);
    }

    /**
     * Restore a trashed file.
     * 
     * @param id
     *            id of the trashed file.
     * @param requestObject
     * @return the file
     * @throws BoxRestException
     * @throws AuthFatalFailureException
     * @throws BoxServerException
     */
    public BoxFile restoreTrashFile(final String id, final BoxItemRestoreRequestObject requestObject) throws BoxRestException, AuthFatalFailureException,
        BoxServerException {
        return (BoxFile) super.restoreTrashItem(id, BoxResourceType.FILE, requestObject);
    }

    /**
     * Get preview of a file.
     * 
     * @param fileId
     *            id of the file
     * @param extension
     *            requested of the preview image file extension
     * @param requestObject
     *            request object
     * @return preview
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public BoxPreview getPreview(final String fileId, final String extension, final BoxImageRequestObject requestObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        PreviewRequest request = new PreviewRequest(getConfig(), getObjectMapper(), fileId, extension, requestObject);
        request.setAuth(getAuth());
        DefaultBoxResponse response = (DefaultBoxResponse) getRestClient().execute(request);
        PreviewResponseParser parser = new PreviewResponseParser();
        ErrorResponseParser errorParser = new ErrorResponseParser(getObjectMapper());
        Object result = response.parseResponse(parser, errorParser);

        return (BoxPreview) tryCastObject(BoxResourceType.PREVIEW, result);
    }

    /**
     * Get thumbnail of a file.
     * 
     * @param fileId
     *            id of the file
     * @param extension
     *            file extension of requested thumbnail
     * @param requestObject
     *            request object
     * @return InputStream
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public InputStream downloadThumbnail(final String fileId, final String extension, final BoxImageRequestObject requestObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        ThumbnailRequest request = new ThumbnailRequest(getConfig(), getObjectMapper(), fileId, extension, requestObject);
        request.setAuth(getAuth());
        DefaultBoxResponse response = (DefaultBoxResponse) getRestClient().execute(request);
        return (InputStream) (new DefaultFileResponseParser()).parse(response);
    }

    /**
     * Upload file/files.
     * 
     * @param requestObject
     *            reqeust object
     * @return newly created box file object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */

    public BoxFile uploadFile(final BoxFileUploadRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        BoxFileUpload upload = new BoxFileUpload(getConfig());
        return upload.execute(this, requestObject);
    }

    /**
     * Copy a file.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            request object
     * @return copied file
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxFile copyFile(final String fileId, final BoxFileRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFile) super.copyItem(fileId, requestObject, BoxResourceType.FILE);
    }

    /**
     * Download a file.
     * 
     * @param fileId
     *            id of the file
     * @param destination
     *            destination of the downloaded file
     * @param listener
     *            listener to monitor the download progress
     * @param requestObject
     *            extra request object going into api request
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws IllegalStateException
     *             exception
     * @throws IOException
     *             exception
     * @throws InterruptedException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public void downloadFile(final String fileId, final File destination, final IFileTransferListener listener, BoxDefaultRequestObject requestObject)
        throws BoxRestException, BoxServerException, IllegalStateException, IOException, InterruptedException, AuthFatalFailureException {
        BoxFileDownload download = new BoxFileDownload(getConfig(), getRestClient(), fileId);
        download.setProgressListener(listener);
        download.execute(getAuth(), destination, getObjectMapper(), requestObject);
    }

    /**
     * Execute the download and return the raw InputStream. This method is not involved with download listeners and will not publish anything through download
     * listeners. Instead caller handles the InputStream as she/he wishes.
     * 
     * @param fileId
     *            id of the file to be downloaded
     * @param requestObject
     *            request object
     * @return InputStream
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public InputStream downloadFile(final String fileId, final BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        BoxFileDownload download = new BoxFileDownload(getConfig(), getRestClient(), fileId);
        return download.execute(getAuth(), getObjectMapper(), requestObject);
    }

    /**
     * Download a file.
     * 
     * @param fileId
     *            id of the file
     * @param outputStreams
     *            OutputStream's the file will be downloaded into
     * @param listener
     *            listener to monitor the download progress
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws IOException
     *             exception
     * @throws InterruptedException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public void downloadFile(final String fileId, final OutputStream[] outputStreams, final IFileTransferListener listener,
        final BoxDefaultRequestObject requestObject) throws BoxRestException, IOException, BoxServerException, InterruptedException, AuthFatalFailureException {
        BoxFileDownload download = new BoxFileDownload(getConfig(), getRestClient(), fileId);
        download.setProgressListener(listener);
        download.execute(getAuth(), outputStreams, getObjectMapper(), requestObject);
    }

    /**
     * Upload a new version of a file.
     * 
     * @param fileId
     *            id of the file
     * @param BoxFileUploadRequestObject
     *            requestObject
     * @return a FileVersion object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxFile uploadNewVersion(final String fileId, final BoxFileUploadRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        BoxFileUpload upload = new BoxFileUpload(getConfig());
        return upload.execute(fileId, this, requestObject);
    }

    /**
     * Get file versions(Note: Versions are only tracked for Box users with premium accounts.).
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            request object
     * @return FileVersions
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public List<BoxFileVersion> getFileVersions(final String fileId, final BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        GetFileVersionsRequest request = new GetFileVersionsRequest(getConfig(), getObjectMapper(), fileId, requestObject);
        BoxCollection collection = (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.FILE_VERSIONS, getObjectMapper());
        return getFileVersions(collection);
    }

    /**
     * Update info for a file.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            request object
     * @return updated file
     * @throws UnsupportedEncodingException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFile updateFileInfo(final String fileId, BoxFileRequestObject requestObject) throws UnsupportedEncodingException, BoxRestException,
        BoxServerException, AuthFatalFailureException {
        return (BoxFile) super.updateItemInfo(fileId, requestObject, BoxResourceType.FILE);
    }

    /**
     * Create a shared link for a file, given the id of the file/folder.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            request object
     * @return the file, with shared link related fields filled in.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxFile createSharedLink(final String fileId, BoxFileRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        return (BoxFile) super.createSharedLink(fileId, requestObject, BoxResourceType.FILE);
    }

    /**
     * Get comments on a file.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            object that goes into request.
     * @return collection of comments
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxCollection getFileComments(final String fileId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        GetFileCommentsRequest request = new GetFileCommentsRequest(getConfig(), getObjectMapper(), fileId, requestObject);
        return (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.COMMENTS, getObjectMapper());
    }

    /**
     * Get files in a collection.
     * 
     * @param collection
     *            collection
     * @return list of files
     */
    public static List<BoxFile> getFiles(BoxCollection collection) {
        List<BoxFile> files = new ArrayList<BoxFile>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxFile) {
                files.add((BoxFile) object);
            }
        }
        return files;
    }

    /**
     * Get file versions in a collection.
     * 
     * @param collection
     *            collection
     * @return list of file versions
     */
    public static List<BoxFileVersion> getFileVersions(BoxCollection collection) {
        List<BoxFileVersion> files = new ArrayList<BoxFileVersion>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxFileVersion) {
                files.add((BoxFileVersion) object);
            }
        }
        return files;
    }
}
