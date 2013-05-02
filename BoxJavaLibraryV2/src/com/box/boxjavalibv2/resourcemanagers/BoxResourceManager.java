package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.exceptions.BoxUnexpectedHttpStatusException;
import com.box.boxjavalibv2.exceptions.BoxUnexpectedStatus;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.responseparsers.BoxObjectResponseParser;
import com.box.boxjavalibv2.responseparsers.ErrorResponseParser;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;
import com.box.restclientv2.requests.DefaultBoxRequest;
import com.box.restclientv2.responses.DefaultBoxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for BoxAPI classes.
 */
public abstract class BoxResourceManager {

    /** BoxConfig. */
    private final IBoxConfig mConfig;
    /** ObjectMapper to be used by Jackson Parser. */
    private final IBoxResourceHub mResourceHub;

    private IBoxRequestAuth mAuth;

    private IBoxRESTClient mRestClient;

    /**
     * private constructor.
     * 
     * @param config
     *            Config
     * @param resourceHub
     *            IResourceHub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxResourceManager(final IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        this.mConfig = config;
        this.mResourceHub = resourceHub;
        this.mAuth = auth;
        this.mRestClient = restClient;
    }

    /**
     * private constructor.
     * 
     * @param config
     *            Config
     * @param resourceHub
     *            IResourceHub
     */
    public BoxResourceManager(final IBoxConfig config, IBoxResourceHub resourceHub) {
        this.mConfig = config;
        this.mResourceHub = resourceHub;
    }

    /**
     * Get auth.
     * 
     * @return auth
     */
    public IBoxRequestAuth getAuth() {
        return mAuth;
    }

    IBoxRESTClient getRestClient() {
        return this.mRestClient;
    }

    /**
     * @return the IResourceHub
     */
    public IBoxResourceHub getResourceHub() {
        return mResourceHub;
    }

    /** Get the ObjectMapper the Jackson JSON parser uses. */
    public ObjectMapper getObjectMapper() {
        return mResourceHub.getObjectMapper();
    }

    /** Get config. */
    public IBoxConfig getConfig() {
        return mConfig;
    }

    /**
     * Execute a request and expect no response body.
     * 
     * @param request
     *            request
     * @throws BoxServerException
     *             excepiton
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    protected void executeRequestWithNoResponseBody(final DefaultBoxRequest request) throws BoxServerException, BoxRestException, AuthFatalFailureException {
        request.setAuth(getAuth());
        DefaultBoxResponse response = (DefaultBoxResponse) getRestClient().execute(request);
        if (response.getExpectedResponseCode() != response.getResponseStatusCode()) {
            throw new BoxServerException("Unexpected response code:" + response.getResponseStatusCode() + ", expecting:" + response.getExpectedResponseCode());
        }
    }

    /**
     * Make a rest api request, get response, parse the response, and try to cast parsed out object into expected object.
     * 
     * @param request
     *            request
     * @param type
     *            type
     * @param objectMapper
     *            ObjectMapper
     * @return parsed object
     * @throws AuthFatalFailureException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     */
    public Object getResponseAndParseAndTryCast(final DefaultBoxRequest request, final BoxResourceType type, final ObjectMapper objectMapper)
        throws BoxRestException, AuthFatalFailureException, BoxServerException {
        Object obj = getResponseAndParse(request, type, objectMapper);
        return tryCastObject(type, obj);
    }

    /**
     * Make a rest api request, get response, and then parse the response.
     * 
     * @param request
     *            request
     * @param type
     *            type
     * @param objectMapper
     *            ObjectMapper
     * @return parsed object
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authenticating totally failed
     */
    public Object getResponseAndParse(final DefaultBoxRequest request, final BoxResourceType type, final ObjectMapper objectMapper) throws BoxRestException,
        AuthFatalFailureException {
        request.setAuth(getAuth());
        DefaultBoxResponse response = (DefaultBoxResponse) getRestClient().execute(request);
        BoxObjectResponseParser parser = new BoxObjectResponseParser(getResourceHub().getClass(type), objectMapper);
        ErrorResponseParser errorParser = new ErrorResponseParser(getObjectMapper());
        return response.parseResponse(parser, errorParser);
    }

    // TODO: support web links
    /**
     * Try to cast a box item into a concrete class(i.e. file or folder)
     * 
     * @param isFolder
     *            whether it's folder
     * @param item
     *            the box item.
     * @return box items
     * @throws BoxServerException
     *             exception
     * @throws BoxRestException
     *             exception
     */
    protected Object tryCastBoxItem(final BoxResourceType type, final Object item) throws BoxServerException, BoxRestException {
        return tryCastObject(type, item);
    }

    /**
     * Try to cast an object into a specific class.
     * 
     * @param expectedType
     *            expected resource type class
     * @param obj
     *            object
     * @return object
     * @throws BoxServerException
     *             exception
     * @throws BoxRestException
     *             exception
     */
    @SuppressWarnings("rawtypes")
    public Object tryCastObject(final BoxResourceType expectedType, final Object obj) throws BoxServerException, BoxRestException {
        if (obj instanceof BoxServerError) {
            throw new BoxServerException((BoxServerError) obj);
        }
        else if (obj instanceof BoxUnexpectedStatus) {
            throw new BoxUnexpectedHttpStatusException((BoxUnexpectedStatus) obj);
        }
        else {
            Class expectedClass = getResourceHub().getClass(expectedType);
            if (expectedClass.isInstance(obj)) {
                return obj;
            }
            else {
                throw new BoxRestException("Invalid class, expected:" + expectedClass.getCanonicalName() + ";current:" + obj.getClass().getCanonicalName());
            }
        }
    }
}
