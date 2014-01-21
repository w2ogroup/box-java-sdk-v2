package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;

public class GetAllGroupsRequestTest extends RequestTestBase {

    @Test
    public void uriTest() {
        Assert.assertEquals("/groups", GetAllGroupsRequest.getUri());
    }

    @Test
    public void testRequestWellFormed() throws BoxRestException, AuthFatalFailureException {
        GetAllGroupsRequest request = new GetAllGroupsRequest(CONFIG, JSON_PARSER, null);
        this.testRequestIsWellFormed(request, BoxConfig.getInstance().getApiUrlAuthority(),
            BoxConfig.getInstance().getApiUrlPath().concat(GetAllGroupsRequest.getUri()), HttpStatus.SC_OK, RestMethod.GET);
    }
}
