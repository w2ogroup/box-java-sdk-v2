package com.box.boxjavalibv2.jsonentities;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.dao.BoxSharedLinkAccess;
import com.box.boxjavalibv2.dao.BoxSharedLinkPermissions;
import com.box.boxjavalibv2.requests.requestobjects.BoxSharedLinkRequestObject;
import com.box.boxjavalibv2.utils.ISO8601DateParser;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SharedLinkRequestObjectTest {

    private final static String PERMISSIONS_STR = "\"permissions\":%s";
    private final static String ACCESS_STR = "\"access\":\"%s\"";
    private final static String UNSHARED_STR = "\"unshared_at\":\"%s\"";

    @Test
    public void testFull() {
        Date date = new Date();
        String access = BoxSharedLinkAccess.OPEN;
        BoxSharedLinkRequestObject entity = BoxSharedLinkRequestObject.createSharedLinkRequestObject(access)
            .setPermissions(new BoxSharedLinkPermissions(true, true)).setUnshared_at(date);

        String accessStr = String.format(ACCESS_STR, access);
        String dateStr = String.format(UNSHARED_STR, ISO8601DateParser.toString(date));

        String entityStr;
        try {
            entityStr = entity.getJSONEntity().toJSONString(new ObjectMapper());
            Assert.assertFalse(entityStr.contains(PERMISSIONS_STR));
            Assert.assertTrue(entityStr.contains(accessStr));
            Assert.assertTrue(entityStr.contains(dateStr));
        }
        catch (BoxRestException e) {
            Assert.fail();
        }
    }

    @Test
    public void testNoUnsharedAt() {
        String access = BoxSharedLinkAccess.OPEN;
        BoxSharedLinkRequestObject entity = BoxSharedLinkRequestObject.createSharedLinkRequestObject(access).setPermissions(
            new BoxSharedLinkPermissions(true, true));

        String accessStr = String.format(ACCESS_STR, access);

        String entityStr;
        try {
            entityStr = entity.getJSONEntity().toJSONString(new ObjectMapper());
            Assert.assertFalse(entityStr.contains(PERMISSIONS_STR));
            Assert.assertTrue(entityStr.contains(accessStr));
            Assert.assertFalse(entityStr.contains("\"unshared_at\":"));
        }
        catch (BoxRestException e) {
            Assert.fail();
        }
    }
}
