package com.box.boxjavalibv2.requests.requestobjects;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;

import com.box.boxjavalibv2.httpentities.MultipartEntityWithProgressListener;
import com.box.boxjavalibv2.interfaces.IFileTransferListener;
import com.box.boxjavalibv2.utils.Constants;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxFileUploadRequestObject extends BoxDefaultRequestObject {

    private MultipartEntityWithProgressListener entity = null;

    private BoxFileUploadRequestObject() {
    }

    /**
     * BoxFileUploadRequestObject for upload files request.
     * 
     * @param parentId
     *            id of the parent folder
     * @param files
     *            files
     * @return BoxFileUploadRequestObject
     * @throws BoxRestException
     */
    public static BoxFileUploadRequestObject uploadFilesRequestObject(final String parentId, final LinkedHashMap<String, File> files) throws BoxRestException {
        return (new BoxFileUploadRequestObject()).setMultipartMIME(getNewFileMultipartEntity(parentId, files));
    }

    /**
     * BoxFileUploadRequestObject for upload file request. Note: for uploading a new version of the file, please use uploadNewVersionRequestObject().
     * 
     * @param parentId
     *            id of the parent folder
     * @param fileName
     *            name of the file
     * @param file
     *            file to be uploaded
     * @return BoxFileUploadRequestObject
     * @throws BoxRestException
     */
    public static BoxFileUploadRequestObject uploadFileRequestObject(final String parentId, final String fileName, final File file) throws BoxRestException {
        LinkedHashMap<String, File> map = new LinkedHashMap<String, File>();
        map.put(fileName, file);
        return BoxFileUploadRequestObject.uploadFilesRequestObject(parentId, map);
    }

    /**
     * BoxFileUploadRequestObject for upload file request. Note: for uploading a new version of the file, please use uploadNewVersionRequestObject().
     * 
     * @param parentId
     *            id of the parent folder
     * @param fileName
     *            name of the file
     * @param inputStream
     *            InputStream of the file to be uploaded
     * @return BoxFileUploadRequestObject
     * @throws BoxRestException
     */
    public static BoxFileUploadRequestObject uploadFileRequestObject(final String parentId, final String fileName, final InputStream inputStream)
        throws BoxRestException {
        return (new BoxFileUploadRequestObject()).setMultipartMIME(getNewFileMultipartEntity(parentId, inputStream, fileName));
    }

    /**
     * BoxFileUploadRequestObject for upload a new version of a file.
     * 
     * @param name
     *            name of the file
     * @param file
     *            file to be uploaded
     * @return BoxFileUploadRequestObject
     * @throws BoxRestException
     *             exception
     */
    public static BoxFileUploadRequestObject uploadNewVersionRequestObject(final String name, final File file) throws BoxRestException {
        return (new BoxFileUploadRequestObject()).setMultipartMIME(getNewVersionMultipartEntity(name, file));
    }

    /**
     * BoxFileUploadRequestObject for upload a new version of a file.
     * 
     * @param name
     *            name of the file
     * @param inputStream
     *            input stream of the file uploaded.
     * @return BoxFileUploadRequestObject
     * @throws BoxRestException
     *             exception
     */
    public static BoxFileUploadRequestObject uploadNewVersionRequestObject(final String name, final InputStream inputStream) throws BoxRestException {
        return (new BoxFileUploadRequestObject()).setMultipartMIME(getNewVersionMultipartEntity(name, inputStream));
    }

    public BoxFileUploadRequestObject setMultipartMIME(final MultipartEntityWithProgressListener mime) throws BoxRestException {
        entity = mime;
        return this;
    }

    /**
     * Set upload listener.
     * 
     * @param listener
     *            upload listener
     */
    public BoxFileUploadRequestObject setListener(IFileTransferListener listener) {
        entity.setListener(listener);
        return this;
    }

    /**
     * Set the content MD5 in the request. This is used in upload file request, it can make sure that the file is not corrupted in transit. In case of the sha1
     * is different than the sha1 calculated on server, request is going to fail.
     * 
     * @param sha1
     *            sha1
     */
    public BoxFileUploadRequestObject setContentMD5(String sha1) {
        addHeader(Constants.CONTENT_MD5, sha1);
        return this;
    }

    /**
     * This is for upload new version request only. Set the If-Match header can be included to ensure that client only overwrites the file if it knows about the
     * latest version.
     * 
     * @param etag
     *            etag
     * @return BoxFileUploadRequestObject
     */
    public BoxFileUploadRequestObject setIfMatch(String etag) {
        addHeader("If-Match", etag);
        return this;
    }

    @Override
    public HttpEntity getEntity() {
        return entity;
    }

    private static MultipartEntityWithProgressListener getNewFileMultipartEntity(final String parentId, final InputStream inputStream, final String fileName)
        throws BoxRestException {
        MultipartEntityWithProgressListener me = new MultipartEntityWithProgressListener(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            me.addPart(Constants.FOLDER_ID, new StringBody(parentId));
        }
        catch (UnsupportedEncodingException e1) {
            throw new BoxRestException(e1);
        }
        me.addPart(fileName, new InputStreamBody(inputStream, fileName));

        return me;
    }

    private static MultipartEntityWithProgressListener getNewFileMultipartEntity(final String parentId, final HashMap<String, File> files)
        throws BoxRestException {
        MultipartEntityWithProgressListener me = new MultipartEntityWithProgressListener(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            me.addPart(Constants.FOLDER_ID, new StringBody(parentId));
        }
        catch (UnsupportedEncodingException e1) {
            throw new BoxRestException(e1);
        }
        for (Map.Entry<String, File> entry : files.entrySet()) {
            me.addPart(entry.getKey(), new FileBody(entry.getValue(), entry.getKey(), "", CharEncoding.UTF_8));
        }
        return me;
    }

    private static MultipartEntityWithProgressListener getNewVersionMultipartEntity(final String name, final File file) {
        MultipartEntityWithProgressListener me = new MultipartEntityWithProgressListener(HttpMultipartMode.BROWSER_COMPATIBLE);
        me.addPart(name, new FileBody(file, name, "", CharEncoding.UTF_8));
        return me;
    }

    private static MultipartEntityWithProgressListener getNewVersionMultipartEntity(final String name, final InputStream inputStream) {
        MultipartEntityWithProgressListener me = new MultipartEntityWithProgressListener(HttpMultipartMode.BROWSER_COMPATIBLE);
        me.addPart(name, new InputStreamBody(inputStream, name));
        return me;
    }
}
