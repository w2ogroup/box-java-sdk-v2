package com.box.boxjavalibv2.request.requestobjects;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.requests.requestobjects.BoxItemRestoreRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoxItemRestoreRequestObjectTest {

    private static final String NAME = "\"name\":\"%s\"";
    private static final String PARENT = "\"parent\":{\"id\":\"%s\"}";

    @Test
    public void testDefaultObjectHasNoField() {
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject();
        Assert.assertTrue(obj.getJSONEntity().isEmpty());
    }

    @Test
    public void testNameInObject() throws BoxRestException {
        String name = "testname";
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject().setNewName(name);
        Assert.assertEquals("{" + String.format(NAME, name) + "}", obj.getJSONEntity().toJSONString(new ObjectMapper()));
    }

    @Test
    public void testParentInObject() throws BoxRestException {
        String parentid = "testid";
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject().setNewParent(parentid);
        Assert.assertEquals("{" + String.format(PARENT, parentid) + "}", obj.getJSONEntity().toJSONString(new ObjectMapper()));
    }

    @Test
    public void testBothParentAndNameInObject() throws BoxRestException {
        String name = "testname";
        String parentid = "testid";
        BoxItemRestoreRequestObject obj = BoxItemRestoreRequestObject.restoreItemRequestObject().setNewName(name).setNewParent(parentid);
        String json = obj.getJSONEntity().toJSONString(new ObjectMapper());
        Assert.assertTrue(json.contains(String.format(NAME, name)));
        Assert.assertTrue(json.contains(String.format(PARENT, parentid)));
    }
}
