package com.box.boxjavalibv2.responseparsers;

import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.boxjavalibv2.exceptions.BoxUnexpectedStatus;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxResponse;
import com.box.restclientv2.responseparsers.DefaultBoxJSONResponseParser;
import com.box.restclientv2.responses.DefaultBoxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parser to parse {@link com.box.restclientv2.responses.DefaultBoxResponse} into {@link com.box.boxjavalibv2.dao.BoxServerError} objects. It analyse the
 * response JSON String and uses <a href="http://jackson.codehaus.org/">Jackson JSON processor</a> to generate {@link com.box.boxjavalibv2.dao.BoxServerError}
 * objects.
 */
public class ErrorResponseParser extends DefaultBoxJSONResponseParser {

    public ErrorResponseParser(ObjectMapper objectMapper) {
        super(BoxServerError.class, objectMapper);
    }

    @Override
    public Object parse(IBoxResponse response) throws BoxRestException {
        if (!(response instanceof DefaultBoxResponse)) {
            throw new BoxRestException("class mismatch, expected:" + DefaultBoxResponse.class.getName() + ";current:" + response.getClass().getName());
        }

        int statusCode = ((DefaultBoxResponse) response).getHttpResponse().getStatusLine().getStatusCode();
        BoxServerError error = null;
        if (isErrorResponse(statusCode)) {
            error = (BoxServerError) super.parse(response);
        }
        else {
            error = new BoxUnexpectedStatus(statusCode);
        }
        error.setHttpStatusCode(statusCode);
        return error;
    }

    private boolean isErrorResponse(int statusCode) {
        return statusCode >= 400 && statusCode < 600;
    }
}
