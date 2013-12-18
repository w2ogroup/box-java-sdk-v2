package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CreateOAuthRequest;
import com.box.boxjavalibv2.requests.RefreshOAuthRequest;
import com.box.boxjavalibv2.requests.RevokeOAuthRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxOAuthRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;

/**
 * API for OAuth. Full details about the Box API can be found at <a href="http://developers.box.com/oauth/">http://developers.box.com/docs</a>
 */
public class BoxOAuthManager extends BoxResourceManager {

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param resourceHub
     *            resource hub
     * @param parser
     *            json parser
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxOAuthManager(final IBoxConfig config, IBoxResourceHub resourceHub, final IBoxJSONParser parser, final IBoxRESTClient restClient) {
        super(config, resourceHub, parser, null, restClient);
    }

    /**
     * Create an OAuth token.
     * 
     * @param requestObject
     *            request object
     * @return OAuth data object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxOAuthToken createOAuth(BoxOAuthRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        CreateOAuthRequest request = new CreateOAuthRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxOAuthToken) getResponseAndParseAndTryCast(request, BoxResourceType.OAUTH_DATA, getJSONParser());
    }

    /**
     * Refresh the OAuth token. Don't call this directly if you enabled OAuth auto-refresh since this is not thread safe. In that case, call
     * boxClient.getOAuthDataController().refresh(), which is thread-safe.
     * 
     * @param requestObject
     *            request object
     * @return OAuth data object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxOAuthToken refreshOAuth(final BoxOAuthRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        RefreshOAuthRequest request = new RefreshOAuthRequest(getConfig(), getJSONParser(), requestObject);
        return (BoxOAuthToken) getResponseAndParseAndTryCast(request, BoxResourceType.OAUTH_DATA, getJSONParser());
    }

    public void revokeOAuth(final BoxOAuthRequestObject requestObject) throws BoxServerException, BoxRestException, AuthFatalFailureException {
        RevokeOAuthRequest request = new RevokeOAuthRequest(getConfig(), getJSONParser(), requestObject);
        executeRequestWithNoResponseBody(request);
    }
}
