package com.box.boxjavalibv2.requests;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class CopyFileFolderRequestTest extends RequestTestBase {

    @Test
    public void uriTest() {
        Assert.assertEquals("/files/123/copy", CopyFileFolderRequest.getUri("123", false));
        Assert.assertEquals("/folders/1234/copy", CopyFileFolderRequest.getUri("1234", true));
    }

    @Test
    public void testFolderRequestWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException {
        testRequestIsWellFormed(true);
    }

    @Test
    public void testFileRequestWellFormed() throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException {
        testRequestIsWellFormed(false);
    }

    private void testRequestIsWellFormed(boolean isFolder) throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException {
        String id = "testid123";
        String parentId = "testparentid456";
        String newName = "testnewname789";

        CopyFileFolderRequest request = new CopyFileFolderRequest(CONFIG, OBJECT_MAPPER, id, BoxFileRequestObject.copyFileRequestObject(parentId).setName(
            newName), isFolder);
        testRequestIsWellFormed(request, BoxConfig.getInstance().getApiUrlAuthority(),
            BoxConfig.getInstance().getApiUrlPath().concat(CopyFileFolderRequest.getUri(id, isFolder)), HttpStatus.SC_CREATED, RestMethod.POST);

        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);
        assertEqualStringEntity(BoxFileRequestObject.copyFileRequestObject(parentId).setName(newName).getJSONEntity(), entity);

    }
}
