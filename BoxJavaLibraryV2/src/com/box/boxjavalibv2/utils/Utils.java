package com.box.boxjavalibv2.utils;

import com.box.boxjavalibv2.BoxClient;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utils class.
 */
public final class Utils {

    /**
     * Private constructor so the class cannot be instantiated.
     */
    private Utils() {
    }

    /**
     * Given a resource type, get the string for it's REST API container. For example, given a {@link BoxResourceType#FILE}, it it's container would be "files".
     * 
     * @param type
     *            type
     * @return container string
     */
    public static String getContainerString(final BoxResourceType type) {
        switch (type) {
            case FILE_VERSION:
                return "versions";
            default:
                return type.toPluralString();
        }
    }

    /**
     * Convert an object to String using Jackson JSON processor.
     * 
     * @param object
     *            object
     * @param objectMapper
     *            object mapper
     * @return String
     * @throws BoxRestException
     *             exception
     */
    public static String convertIJSONStringEntitytoString(final Object object, ObjectMapper objectMapper) throws BoxRestException {
        try {
            return objectMapper.writeValueAsString(object);
        }
        catch (Exception e) {
            throw new BoxRestException(e);
        }
    }

    /**
     * Convert an object to String using Jackson JSON processor.
     * 
     * @param object
     *            object
     * @param client
     *            BoxClient you are using
     * @return String
     * @throws BoxRestException
     */
    public static String convertIJSONStringEntitytoString(final Object object, BoxClient client) throws BoxRestException {
        return convertIJSONStringEntitytoString(object, client.getResourceHub().getObjectMapper());
    }

    /**
     * Convert an object to String using default objectmapper.
     * 
     * @param object
     *            object
     * @param client
     *            BoxClient you are using
     * @return String
     * @throws BoxRestException
     */
    public static String convertIJSONStringEntitytoString(final Object object) throws BoxRestException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return convertIJSONStringEntitytoString(object, objectMapper);
    }

    /**
     * Use Jackson JSON processor to parse a JSON string into an objecct using a default object mapper.
     * 
     * @param jsonString
     *            json string
     * @param theClass
     *            class
     * @return object
     */
    public static <T> T parseJSONStringIntoObject(final String jsonString, final Class<T> theClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return parseJSONStringIntoObject(jsonString, theClass, objectMapper);
    }

    /**
     * Use Jackson JSON processor to parse a JSON string into an objecct.
     * 
     * @param jsonString
     *            json string
     * @param theClass
     *            class
     * @param objectMapper
     *            mapper.
     * @return
     * @return object
     */
    public static <T> T parseJSONStringIntoObject(final String jsonString, final Class<T> theClass, final ObjectMapper objectMapper) {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createJsonParser(jsonString);
            return objectMapper.readValue(jp, theClass);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Use Jackson JSON processor to parse a JSON string into an objecct.
     * 
     * @param jsonString
     *            json string
     * @param theClass
     *            class
     * @param client
     *            the BoxClient you are using
     * @return object
     */
    public static <T> T parseJSONStringIntoObject(final String jsonString, final Class<T> theClass, BoxClient client) {
        return parseJSONStringIntoObject(jsonString, theClass, client.getResourceHub().getObjectMapper());
    }
}
