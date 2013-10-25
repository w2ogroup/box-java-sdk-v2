package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.exceptions.BoxJSONException;

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
     * @throws BoxJSONException
     */
    String toJSONString(IBoxJSONParser parser) throws BoxJSONException;
}
