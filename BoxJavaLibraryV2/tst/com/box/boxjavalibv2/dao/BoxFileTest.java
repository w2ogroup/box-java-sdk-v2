package com.box.boxjavalibv2.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.exceptions.BoxRestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BoxFileTest {

    // @Test
    public void testParcelRoundTrip() throws BoxRestException, IOException {
        String fileJson = FileUtils.readFileToString(new File("testdata/file.json"));
        BoxFile filev2 = (BoxFile) TestUtils.getFromJSON(fileJson, BoxFile.class);

        TestParcel parcel = new TestParcel();
        filev2.writeToParcel(parcel, 0);
        BoxFile fromParcel = new BoxFile(parcel);

        Assert.assertEquals("file", fromParcel.getType());
        Assert.assertEquals("testfileid", fromParcel.getId());
        Assert.assertEquals("testsha1", fromParcel.getSha1());
        Assert.assertEquals("2", fromParcel.getVersionNumber());
        Assert.assertEquals(2, (int) fromParcel.getCommentCount());

    }

    @Test
    public void foo() throws IOException {
        String fileJson = FileUtils.readFileToString(new File("testdata/file2.json"));
        Gson gson = (new GsonBuilder()).registerTypeHierarchyAdapter(BoxFile.class, new FileDeserializer())
            .registerTypeHierarchyAdapter(BoxFile.class, new BFileSerializer()).registerTypeHierarchyAdapter(BoxFolder.class, new FolderDeserializer())
            .create();
        BoxFile f = gson.fromJson(fileJson, BoxFile.class);
        gson.toJson(f);
    }

    private static class FileDeserializer implements JsonDeserializer<BoxFile> {

        @Override
        public BoxFile deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject j = (JsonObject) element;

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("type", "file");
            map.put("id", j.get("id").getAsString());
            JsonElement obj = j.get("parent");
            if (obj != null) {
                map.put("parent", context.deserialize(obj, BoxFolder.class));
            }
            BoxFile f = new BoxFile(map);
            return f;
        }
    }

    private static class BFileSerializer implements JsonSerializer<BoxFile> {

        @Override
        public JsonElement serialize(BoxFile f, Type type, JsonSerializationContext context) {
            JsonObject j = new JsonObject();
            j.add("type", new JsonPrimitive(f.getType()));
            return j;
        }

    }

    private static class FolderDeserializer implements JsonDeserializer<BoxFolder> {

        @Override
        public BoxFolder deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject j = (JsonObject) element;

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("type", "folders");
            map.put("id", j.get("id").getAsString());
            JsonElement obj = j.get("parent");
            if (obj != null) {
                map.put("parent", context.deserialize(obj, BoxFolder.class));
            }
            BoxFolder f = new BoxFolder(map);
            return f;
        }
    }
}
