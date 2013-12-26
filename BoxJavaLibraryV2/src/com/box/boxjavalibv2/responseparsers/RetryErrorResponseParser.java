package com.box.boxjavalibv2.responseparsers;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxResponse;
import com.box.restclientv2.responses.DefaultBoxResponse;

/**
 * Parser to parse {@link com.box.restclientv2.responses.DefaultBoxResponse} into {@link com.box.boxjavalibv2.dao.BoxServerError} objects. It analyse the
 * response JSON String and uses <a href="http://jackson.codehaus.org/">Jackson JSON processor</a> to generate {@link com.box.boxjavalibv2.dao.BoxServerError}
 * objects.
 */
public class RetryErrorResponseParser extends ErrorResponseParser {

    private static final String RETRY_AFTER = "Retry-After";

    public RetryErrorResponseParser(final IBoxJSONParser parser) {
        super(parser);
    }

    @Override
    public Object parse(IBoxResponse response) throws BoxRestException {
        Object error = super.parse(response);
        if (error instanceof BoxServerError) {
            HttpResponse httpResponse = ((DefaultBoxResponse) response).getHttpResponse();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (isRetryAccepted(statusCode)) {
                Header header = ((DefaultBoxResponse) response).getHttpResponse().getFirstHeader(RETRY_AFTER);
                if (header != null) {
                    String value = header.getValue();
                    ((BoxServerError) error).setRetryAfter(Integer.valueOf(value));
                }
            }

        }
        return error;
    }

   

    private boolean isRetryAccepted(int statusCode) {
        return statusCode == HttpStatus.SC_ACCEPTED;
    }
}
