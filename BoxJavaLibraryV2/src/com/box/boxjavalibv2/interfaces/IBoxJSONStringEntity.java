package com.box.boxjavalibv2.interfaces;

import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Interface for classes that can be converted to JSON Strings.
 */
public interface IBoxJSONStringEntity {

    /**
     * Convert to JSON String.
     * 
     * @param objectMapper
     *            object mapper
     * @return JSON String
     * @throws BoxRestException
     *             exception
     */
    String toJSONString(ObjectMapper objectMapper) throws BoxRestException;
}
