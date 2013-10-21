package com.box.boxjavalibv2.interfaces;

import com.box.restclientv2.exceptions.BoxRestException;

/**
 * Interface for classes that can be converted to JSON Strings.
 */
public interface IBoxJSONStringEntity {

    /**
     * Convert to JSON String.
     * 
     * @param parser
     *            json parser
     * @return JSON String
     * @throws BoxRestException
     *             exception
     */
    String toJSONString(IBoxJSONParser parser) throws BoxRestException;
}
