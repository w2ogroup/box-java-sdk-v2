package com.box.boxjavalibv2.resourcemanagers;

import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.GetSharedItemRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

public class BoxSharedItemsManager extends BoxResourceManager {

    /**
     * Constructor.
     * 
     * @param config
     *            config
     * @param resourceHub
     *            IResourceHub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxSharedItemsManager(IBoxConfig config, IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get the shared item given a SharedItemAuth.
     * 
     * @param auth
     *            SharedItemAuth, which contains information of the shared link.
     * @param requestObject
     *            request object
     * @return the shared object referred to by the shared link.
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception indicating authentication totally failed
     */
    public BoxItem getSharedItem(final BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        GetSharedItemRequest request = new GetSharedItemRequest(getConfig(), getObjectMapper(), requestObject);
        return (BoxItem) getResponseAndParseAndTryCast(request, BoxResourceType.ITEM, getObjectMapper());
    }
}
