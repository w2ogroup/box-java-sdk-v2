package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class GetFileFolderRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/files/123", GetFileFolderRequest.getUri("123", false));
        Assert.assertEquals("/folders/123", GetFileFolderRequest.getUri("123", true));
    }

    @Test
    public void testFileRequestIsWellFormed() throws BoxRestException, AuthFatalFailureException {
        testRequestIsWellFormed(false);
    }

    @Test
    public void testFolderRequestIsWellFormed() throws BoxRestException, AuthFatalFailureException {
        testRequestIsWellFormed(true);
    }

    public void testRequestIsWellFormed(boolean isFolder) throws BoxRestException, AuthFatalFailureException {
        String id = "testid123";

        GetFileFolderRequest request = new GetFileFolderRequest(CONFIG, OBJECT_MAPPER, id, isFolder, null);
        testRequestIsWellFormed(request, BoxConfig.getInstance().getApiUrlAuthority(),
            BoxConfig.getInstance().getApiUrlPath().concat(GetFileFolderRequest.getUri(id, isFolder)), HttpStatus.SC_OK, RestMethod.GET);

    }
}