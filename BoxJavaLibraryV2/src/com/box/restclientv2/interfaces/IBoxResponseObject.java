package com.box.restclientv2.interfaces;

import com.box.restclientv2.exceptions.BoxRestException;

/**
 * Interface for the class of object to be converted from API response. Although this interface is on REST client level, application level can still implement
 * this interface and directly use the object converted from response in their application level. Implementation of other interfaces(such as Parcelable) may be
 * needed then.
 */
public interface IBoxResponseObject {

    /**
     * Parese the API response into this object.
     * 
     * @param response
     *            API response.
     * @throws BoxRestException
     */
    void parse(IBoxResponse response) throws BoxRestException;
}
