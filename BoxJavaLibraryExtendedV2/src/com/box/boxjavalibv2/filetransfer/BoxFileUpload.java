package com.box.boxjavalibv2.filetransfer;

import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxMalformedResponseException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IFileTransferListener;
import com.box.boxjavalibv2.requests.UploadFileRequest;
import com.box.boxjavalibv2.requests.UploadNewVersionFileRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileUploadRequestObject;
import com.box.boxjavalibv2.resourcemanagers.BoxFilesManager;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;

/**
 * Contains logic for uploading a user's file via Box API and supports using {@link IFileTransferListener} to monitor uploading progress.
 */
public class BoxFileUpload {

    /** Config. */
    private final IBoxConfig mConfig;

    /**
     * Constructor.
     * 
     * @param config
     *            config
     */
    public BoxFileUpload(final IBoxConfig config) {
        this.mConfig = config;
    }

    /**
     * Execute the upload task.
     * 
     * @param manager
     *            BoxFilesManager
     * @param requestObject
     *            request objecct
     * @return the list of the uploaded BoxFiles
     * @throws BoxServerException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public List<BoxFile> execute(BoxFilesManager manager, BoxFileUploadRequestObject requestObject) throws BoxServerException, BoxRestException,
        AuthFatalFailureException {
        UploadFileRequest request = new UploadFileRequest(mConfig, manager.getObjectMapper(), requestObject);
        Object result = manager.getResponseAndParse(request, BoxResourceType.FILES, manager.getObjectMapper());
        BoxCollection collection = (BoxCollection) manager.tryCastObject(BoxResourceType.FILES, result);
        return BoxFilesManager.getFiles(collection);
    }

    /**
     * Upload a new version of file with known file id and sha1.
     * 
     * @param fileId
     *            id of the file
     * @param requestObject
     *            request objecct
     * @return the new FileVersionV2 object
     * @throws BoxServerException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxFile execute(final String fileId, BoxFilesManager manager, BoxFileUploadRequestObject requestObject) throws BoxServerException, BoxRestException,
        AuthFatalFailureException {
        UploadNewVersionFileRequest request = new UploadNewVersionFileRequest(mConfig, manager.getObjectMapper(), fileId, requestObject);
        Object result = manager.getResponseAndParse(request, BoxResourceType.FILE_VERSIONS, manager.getObjectMapper());
        BoxCollection versions = (BoxCollection) manager.tryCastObject(BoxResourceType.FILE_VERSIONS, result);
        if (versions.getTotalCount() != 1) {
            throw new BoxMalformedResponseException();
        }
        return (BoxFile) versions.getEntries().get(0);
    }
}
