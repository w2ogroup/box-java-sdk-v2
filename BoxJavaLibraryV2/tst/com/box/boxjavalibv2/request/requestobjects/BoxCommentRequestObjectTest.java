package com.box.boxjavalibv2.request.requestobjects;

import junit.framework.Assert;

import org.junit.Test;

import com.box.boxjavalibv2.requests.requestobjects.BoxCommentRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoxCommentRequestObjectTest {

    private static final String JSON_STR = "{\"message\":\"%s\"}";

    @Test
    public void testJSONHasAllFields() throws BoxRestException {
        String message = "testmessage123";
        BoxCommentRequestObject entity = BoxCommentRequestObject.updateCommentRequestObject(message);

        Assert.assertEquals(String.format(JSON_STR, message), entity.getJSONEntity().toJSONString(new ObjectMapper()));
    }

}
