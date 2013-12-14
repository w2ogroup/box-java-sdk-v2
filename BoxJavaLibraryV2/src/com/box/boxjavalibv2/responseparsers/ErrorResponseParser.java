package com.box.boxjavalibv2.responseparsers;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.box.boxjavalibv2.dao.BoxGenericServerError;
import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.boxjavalibv2.exceptions.BoxUnexpectedStatus;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxResponse;
import com.box.restclientv2.responseparsers.DefaultBoxJSONResponseParser;
import com.box.restclientv2.responses.DefaultBoxResponse;

/**
 * Parser to parse {@link com.box.restclientv2.responses.DefaultBoxResponse} into {@link com.box.boxjavalibv2.dao.BoxServerError} objects. It analyse the
 * response JSON String and uses <a href="http://jackson.codehaus.org/">Jackson JSON processor</a> to generate {@link com.box.boxjavalibv2.dao.BoxServerError}
 * objects.
 */
public class ErrorResponseParser extends DefaultBoxJSONResponseParser {

    public ErrorResponseParser(final IBoxJSONParser parser) {
        super(BoxServerError.class, parser);
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
        error.setStatus(statusCode);
        return error;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object parseInputStream(InputStream in) {
        String errorStr = null;
        try {
            errorStr = IOUtils.toString(in);
            Object obj = getParser().parseIntoBoxObject(errorStr, getObjectClass());
            // JSON parser falls back to BoxTypedObject when there is no "type" in error String. In this case, this "BoxTypedObject" does not really make sense
            // and should not be used.
            if (obj instanceof BoxServerError) {
                return obj;
            }
        }
        catch (Exception e) {
            if (StringUtils.isEmpty(errorStr)) {
                errorStr = e.getMessage();
            }
        }

        BoxGenericServerError genericE = new BoxGenericServerError();
        genericE.setMessage(errorStr);
        return genericE;
    }

    private boolean isErrorResponse(int statusCode) {
        return statusCode >= 400 && statusCode < 600;
    }
}
