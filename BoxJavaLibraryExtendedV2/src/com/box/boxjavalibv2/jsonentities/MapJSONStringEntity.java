package com.box.boxjavalibv2.jsonentities;

import java.util.LinkedHashMap;

import com.box.boxjavalibv2.interfaces.IBoxJSONStringEntity;
import com.box.boxjavalibv2.utils.Utils;
import com.box.restclientv2.exceptions.BoxRestException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implemenation of {@link IBoxJSONStringEntity} based on LinkedHashMap.
 */
public class MapJSONStringEntity extends LinkedHashMap<String, Object> implements IBoxJSONStringEntity {

    private static final long serialVersionUID = 1L;

    @Override
    public String toJSONString(ObjectMapper objectMapper) throws BoxRestException {
        return Utils.convertIJSONStringEntitytoString(this, objectMapper);
    }

}
