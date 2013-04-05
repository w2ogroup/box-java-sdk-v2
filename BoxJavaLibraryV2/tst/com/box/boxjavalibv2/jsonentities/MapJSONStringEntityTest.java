package com.box.boxjavalibv2.jsonentities;

import junit.framework.Assert;

import org.junit.Test;

import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapJSONStringEntityTest {

    @Test
    public void testJson() {
        String json = "{\"%s\":\"%s\"}";
        String name = "testname";
        String value = "testvalue";
        MapJSONStringEntity entity = new MapJSONStringEntity();

        entity.put(name, value);
        try {
            Assert.assertEquals(String.format(json, name, value), entity.toJSONString(new ObjectMapper()));
        }
        catch (BoxRestException e) {
            Assert.fail();
        }
    }
}
