package com.box.restclientv2.authorization.oauthmultithread;

import com.box.boxjavalibv2.BoxConfig;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockRequest extends DefaultBoxRequest {

    public MockRequest() throws BoxRestException {
        super(BoxConfig.getInstance(), new ObjectMapper(), "uri", RestMethod.GET, null);
    }

}
