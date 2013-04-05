package com.box.boxjavalibv2.requests;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.requests.requestobjects.BoxFileRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class UpdateFileFolderInfoRequestTest extends RequestTestBase {

    @Test
    public void testUri() {
        Assert.assertEquals("/files/123", UpdateFileFolderInfoRequest.getUri("123", false));
        Assert.assertEquals("/folders/123", UpdateFileFolderInfoRequest.getUri("123", true));
    }

    @Test
    public void testFolderRequestIsWellFormed() throws IllegalStateException, BoxRestException, IOException, AuthFatalFailureException {
        testRequestIsWellFormed(true);
    }

    @Test
    public void testFileRequestIsWellFormed() throws IllegalStateException, BoxRestException, IOException, AuthFatalFailureException {
        testRequestIsWellFormed(false);
    }

    public void testRequestIsWellFormed(boolean isFolder) throws BoxRestException, IllegalStateException, IOException, AuthFatalFailureException {
        String id = "testid123";
        String parentId = "testparentid456";

        UpdateFileFolderInfoRequest request = new UpdateFileFolderInfoRequest(CONFIG, OBJECT_MAPPER, id, BoxFileRequestObject.updateFileRequestObject()
            .setParent(parentId), isFolder);
        testRequestIsWellFormed(request, BoxConfig.getInstance().getApiUrlAuthority(),
            BoxConfig.getInstance().getApiUrlPath().concat(UpdateFileFolderInfoRequest.getUri(id, isFolder)), HttpStatus.SC_OK, RestMethod.PUT);

        HttpEntity entity = request.getRequestEntity();
        Assert.assertTrue(entity instanceof StringEntity);
        assertEqualStringEntity(BoxFileRequestObject.updateFileRequestObject().setParent(parentId).getJSONEntity(), entity);
    }
}