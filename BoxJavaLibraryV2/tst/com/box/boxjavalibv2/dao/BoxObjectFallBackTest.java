package com.box.boxjavalibv2.dao;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.box.boxjavalibv2.testutils.TestUtils;
import com.box.restclientv2.exceptions.BoxRestException;

public class BoxObjectFallBackTest {

    @Test
    public void testFallBack() throws IOException, BoxRestException {
        String json = FileUtils.readFileToString(new File("testdata/wrongtype.json"));
        BoxTypedObject obj = (BoxTypedObject) TestUtils.getFromJSON(json, BoxTypedObject.class);
        Assert.assertEquals(BoxTypedObject.class, obj.getClass());

        BoxItem obj1 = (BoxItem) TestUtils.getFromJSON(json, BoxItem.class);
        Assert.assertEquals(BoxItem.class, obj1.getClass());
    }
}
