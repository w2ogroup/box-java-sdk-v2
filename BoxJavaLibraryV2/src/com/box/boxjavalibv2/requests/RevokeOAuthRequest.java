package com.box.boxjavalibv2.requests;

import com.box.boxjavalibv2.requests.requestobjects.BoxOAuthRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RevokeOAuthRequest extends DefaultBoxRequest {

    public static final String URI = "/oauth2/revoke";

    public RevokeOAuthRequest(IBoxConfig config, ObjectMapper objectMapper, BoxOAuthRequestObject requestObject) throws BoxRestException {
        super(config, objectMapper, getUri(), RestMethod.POST, requestObject);
    }

    public static String getUri() {
        return URI;
    }
}
