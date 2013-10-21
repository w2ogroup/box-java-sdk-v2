package com.box.boxjavalibv2.jacksonparser;

import java.io.InputStream;

import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;

public class BoxJacksonJSONParser implements IBoxJSONParser {

    private final ObjectMapper mObjectMapper;

    public BoxJacksonJSONParser() {
        mObjectMapper = new ObjectMapper();
        mObjectMapper.setSerializationInclusion(Include.NON_NULL);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void registerSubtype(final Class<?> cls, final String typeString) {
        mObjectMapper.registerSubtypes(new NamedType(cls, typeString));
    }

    private ObjectMapper getObjectMapper() {
        return mObjectMapper;
    }

    @Override
    public String convertIJSONStringEntitytoString(final Object object) throws BoxRestException {
        try {
            return getObjectMapper().writeValueAsString(object);
        }
        catch (Exception e) {
            throw new BoxRestException(e);
        }
    }

    @Override
    public <T> T parseJSONStringIntoObject(final InputStream inputStream, final Class<T> theClass) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createJsonParser(inputStream);
            return objectMapper.readValue(jp, theClass);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> T parseJSONStringIntoObject(final String jsonString, final Class<T> theClass) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createJsonParser(jsonString);
            return objectMapper.readValue(jp, theClass);
        }
        catch (Exception e) {
            return null;
        }
    }
}
