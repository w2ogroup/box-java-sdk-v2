package com.box.restclientv2.interfaces;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 * Class to analyse http request and response. This can be used for network anaysis purpose.
 */
public interface IBoxRestVisitor {

    /**
     * Visit the http request right before request sent out.
     * 
     * @param request
     *            http request
     */
    void visitRequestBeforeSend(HttpRequest request);

    /**
     * Visit the http response after response is received.
     * 
     * @param response
     *            http response.
     */
    void visitResponseUponReceiving(HttpResponse response);

    /**
     * Visit the exception when exception is thrown during http call.
     * 
     * @param e
     *            Exception.
     */
    void visitException(Exception e);
}
