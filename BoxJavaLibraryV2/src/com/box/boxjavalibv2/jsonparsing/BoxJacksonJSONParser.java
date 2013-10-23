package com.box.boxjavalibv2.jsonparsing;

import java.io.InputStream;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;

public class BoxJacksonJSONParser implements IBoxJSONParser {

    private final ObjectMapper mObjectMapper;

    public BoxJacksonJSONParser(final IBoxResourceHub hub) {
        mObjectMapper = new ObjectMapper();
        mObjectMapper.setSerializationInclusion(Include.NON_NULL);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for (BoxResourceType type : BoxResourceType.values()) {
            mObjectMapper.registerSubtypes(new NamedType(hub.getClass(type), type.toString()));
        }
    }

    protected ObjectMapper getObjectMapper() {
        return mObjectMapper;
    }

    @Override
    public String convertToString(final Object object) throws BoxRestException {
        try {
            return getObjectMapper().writeValueAsString(object);
        }
        catch (Exception e) {
            throw new BoxRestException(e);
        }
    }

    @Override
    public <T> T parseIntoObject(final InputStream inputStream, final Class<T> theClass) {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createJsonParser(inputStream);
            return getObjectMapper().readValue(jp, theClass);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> T parseIntoObject(final String jsonString, final Class<T> theClass) {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createJsonParser(jsonString);
            return getObjectMapper().readValue(jp, theClass);
        }
        catch (Exception e) {
            return null;
        }
    }
}
