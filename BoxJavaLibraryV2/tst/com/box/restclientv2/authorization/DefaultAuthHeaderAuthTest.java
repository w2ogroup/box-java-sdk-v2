package com.box.restclientv2.authorization;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.BoxConfig;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultAuthHeaderAuthTest {

    @Test
    public void testSetAuth() throws BoxRestException, AuthFatalFailureException {
        DefaultBoxRequest request = new DefaultBoxRequest(BoxConfig.getInstance(), new ObjectMapper(), "fakeuri", RestMethod.GET, null);
        String token = "test token";
        String apiKey = "test api key";
        String deviceId = "f9h30fhflzkhg84ghgzhgr534653";
        String deviceName = "Galaxy Death Star";
        DefaultAuthHeaderAuth auth = new DefaultAuthHeaderAuth(token, apiKey, deviceId, deviceName);
        auth.setAuth(request);
        request.prepareRequest();
        Assert.assertEquals(auth.getAuthString().toString(), request.getRawRequest().getHeaders(DefaultAuthHeaderAuth.AUTH_HEADER_NAME)[0].getValue());
    }
}
