package com.box.boxjavalibv2.requests;

import org.apache.http.HttpStatus;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.restclientv2.RestMethod;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.requests.DefaultBoxRequest;

public class DeleteTrashItemRequest extends DefaultBoxRequest {

    private static final String URI = "/%s/%s/trash";

    public DeleteTrashItemRequest(final IBoxConfig config, final IBoxJSONParser parser, final String id, final BoxResourceType resourceType,
        final BoxDefaultRequestObject requestObject) throws BoxRestException {
        super(config, parser, getUri(id, resourceType), RestMethod.DELETE, requestObject);
        this.setExpectedResponseCode(HttpStatus.SC_NO_CONTENT);
    }

    /**
     * Get uri.
     * 
     * @param id
     *            id of the item
     * @param itemType
     *            type of the item
     * @return uri
     */
    public static String getUri(final String id, final BoxResourceType itemType) {
        return String.format(URI, itemType.toPluralString(), id);
    }
}
