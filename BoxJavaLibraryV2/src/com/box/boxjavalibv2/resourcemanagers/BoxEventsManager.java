package com.box.boxjavalibv2.resourcemanagers;

import java.util.ArrayList;
import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxEvent;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.EventOptionsRequest;
import com.box.boxjavalibv2.requests.GetEventsRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxEventRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

/**
 * Use this class to execute requests <b>synchronously</b> against the Box REST API(V2). Full details about the Box API can be found at {@see <a
 * href="http://developers.box.com/docs">http://developers.box.com/docs</a>} . You must have an OpenBox application with a valid API key to use the Box API. All
 * methods in this class are executed in the invoking thread, and therefore are NOT safe to execute in the UI thread of your application. You should only use
 * this class if you already have worker threads or AsyncTasks that you want to incorporate the Box API into.
 */
public class BoxEventsManager extends BoxResourceManager {

    /**
     * Constructor.
     * 
     * @param config
     *            BoxConfig
     * @param resourceHub
     *            IResourceHub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxEventsManager(IBoxConfig config, IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get Events.
     * 
     * @param stream_position
     * @param stream_type
     * @param limit
     * @param requestObject
     * @return
     * @throws BoxRestException
     * @throws BoxServerException
     * @throws AuthFatalFailureException
     */
    public BoxCollection getEvents(BoxEventRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        GetEventsRequest request = new GetEventsRequest(getConfig(), getObjectMapper(), requestObject);
        return (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.ITEMS, getObjectMapper());
    }

    /**
     * Get Events options.
     * 
     * @param requestObject
     * @return
     * @throws BoxRestException
     * @throws BoxServerException
     * @throws AuthFatalFailureException
     */
    public BoxCollection getEventOptions(BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        EventOptionsRequest request = new EventOptionsRequest(getConfig(), getObjectMapper(), requestObject);
        return (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.ITEMS, getObjectMapper());
    }

    /**
     * Get comments from a collection.
     * 
     * @param collection
     *            collection
     * @return comments
     */
    public static List<BoxEvent> getEvents(BoxCollection collection) {
        List<BoxEvent> events = new ArrayList<BoxEvent>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxEvent) {
                events.add((BoxEvent) object);
            }
        }
        return events;
    }
}
