package com.box.boxjavalibv2.requests;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import junit.framework.Assert;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileUploadRequestObject;
import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * Test UploadFileRequest.
 */
public class UploadFileRequestTest extends RequestTestBase {

    /**
     * Test uri.
     */
    @Test
    public void testUri() {
        Assert.assertEquals("/files/content", UploadFileRequest.getUri());
    }

    /**
     * Test reqeust is well formed.
     * 
     * @throws IOException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     */
    @Test
    public void testRequstIsWellFormed() throws IOException, BoxRestException, AuthFatalFailureException {
        String parentId = "testid123";
        String content = "testcontent456";
        File f = null;
        f = TestUtils.createTempFile(content);

        String fileName = "testfilename998";
        LinkedHashMap<String, File> files = new LinkedHashMap<String, File>();
        files.put(fileName, f);

        UploadFileRequest request = new UploadFileRequest(CONFIG, OBJECT_MAPPER, BoxFileUploadRequestObject.uploadFilesRequestObject(parentId, files));
        testRequestIsWellFormed(request, BoxConfig.getInstance().getUploadUrlAuthority(),
            BoxConfig.getInstance().getUploadUrlPath().concat(UploadFileRequest.getUri()), HttpStatus.SC_CREATED, RestMethod.POST);
    }
}
