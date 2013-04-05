package com.box.boxjavalibv2.responseparsers;

import com.box.restclientv2.responseparsers.DefaultBoxJSONResponseParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Parser to parse {@link com.box.restclientv2.responses.DefaultBoxResponse} into Box DAO objects. It analyse the response JSON String and uses <a
 * href="http://jackson.codehaus.org/">Jackson JSON processor</a> to generate Box DAO objects.
 */
@SuppressWarnings("rawtypes")
public class BoxObjectResponseParser extends DefaultBoxJSONResponseParser {

    /**
     * Constructor.
     * 
     * @param type
     *            type of the box resource
     * 
     * @param cls
     *            Object class.
     */
    public BoxObjectResponseParser(Class cls, ObjectMapper objectMapper) {
        super(cls, objectMapper);
    }
}
