package com.box.restclientv2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequest;
import com.box.restclientv2.interfaces.IBoxResponse;
import com.box.restclientv2.responses.DefaultBoxResponse;

/**
 * Basic implementation of the {@link IBoxRESTClient}.
 */
public class BoxBasicRestClient implements IBoxRESTClient {

    final DefaultHttpClient mHttpClient;

    public HttpClient getRawHttpClient() {
        return mHttpClient;
    }

    /**
     * Constructor.
     */
    public BoxBasicRestClient() {
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 5000);
        ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRoute() {

            @Override
            public int getMaxForRoute(HttpRoute httpRoute) {
                return 100;
            }
        });

        SchemeRegistry schemeReg = new SchemeRegistry();
        schemeReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager(params, schemeReg);
        mHttpClient = new DefaultHttpClient(connectionManager, params);
    }

    @Override
    public IBoxResponse execute(final IBoxRequest boxRequest) throws BoxRestException, AuthFatalFailureException {
        HttpUriRequest httpRequest = boxRequest.prepareRequest();
        HttpResponse response;

        try {
            response = getRawHttpClient().execute(httpRequest);
        }
        catch (Exception e) {
            throw new BoxRestException(e);
        }
        DefaultBoxResponse boxResponse = new DefaultBoxResponse(response);
        boxResponse.setExpectedResponseCode(boxRequest.getExpectedResponseCode());
        return boxResponse;
    }

}
