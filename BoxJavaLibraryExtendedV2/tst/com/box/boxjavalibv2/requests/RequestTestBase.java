package com.box.boxjavalibv2.requests;

import java.io.IOException;
import java.net.URI;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.interfaces.IBoxJSONStringEntity;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestTestBase {

    protected static final IBoxConfig CONFIG = BoxConfig.getInstance();
    protected static final String SCHEME = CONFIG.getApiUrlScheme();
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected void testRequestIsWellFormed(DefaultBoxRequest request, String expectedHost, String expectedUriPath, int expectedReturnCode,
        RestMethod expectedMethod) throws BoxRestException, AuthFatalFailureException {

        request.prepareRequest();

        Assert.assertEquals(expectedReturnCode, request.getExpectedResponseCode());
        Assert.assertEquals(expectedMethod, request.getRestMethod());
        URI uri = request.getRawRequest().getURI();
        Assert.assertEquals(expectedHost, uri.getHost());
        Assert.assertEquals(SCHEME, uri.getScheme());
        Assert.assertEquals(expectedUriPath, uri.getPath());
    }

    protected void assertEqualStringEntity(IBoxJSONStringEntity expected, HttpEntity current) throws IllegalStateException, BoxRestException, IOException {

        Assert.assertEquals(expected.toJSONString(OBJECT_MAPPER), IOUtils.toString(current.getContent()));

    }
}
