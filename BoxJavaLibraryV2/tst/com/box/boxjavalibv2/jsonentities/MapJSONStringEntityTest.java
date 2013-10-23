package com.box.boxjavalibv2.jsonentities;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.jsonparsing.BoxJacksonJSONParser;
import com.box.boxjavalibv2.jsonparsing.BoxResourceHub;
import com.box.restclientv2.exceptions.BoxRestException;

public class MapJSONStringEntityTest {

    @Test
    public void testJson() {
        String json = "{\"%s\":\"%s\"}";
        String name = "testname";
        String value = "testvalue";
        MapJSONStringEntity entity = new MapJSONStringEntity();

        entity.put(name, value);
        try {
            Assert.assertEquals(String.format(json, name, value), entity.toJSONString(new BoxJacksonJSONParser(new BoxResourceHub())));
        }
        catch (BoxRestException e) {
            Assert.fail();
        }
    }
}
