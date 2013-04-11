package com.box.boxjavalibv2.testutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.box.boxjavalibv2.dao.BoxObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public static File createTempFile(String fileContent) throws IOException {
        File tmp = File.createTempFile("tmp", "tmp");
        FileUtils.writeStringToFile(tmp, fileContent);
        return tmp;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static BoxObject getFromJSON(String json, Class cls) throws BoxRestException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser jp = jsonFactory.createJsonParser(json);
            return (BoxObject) objectMapper.readValue(jp, cls);
        }
        catch (Exception e) {
            throw new BoxRestException(e, e.getMessage());
        }
    }
}
