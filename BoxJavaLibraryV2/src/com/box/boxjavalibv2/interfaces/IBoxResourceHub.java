package com.box.boxjavalibv2.interfaces;

import com.box.boxjavalibv2.dao.BoxResourceType;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IBoxResourceHub {

    /**
     * Given a {@link BoxResourceType}, get the corrosponding DAO class.
     * 
     * @param type
     *            resource type
     * @return corresponding resource DAO class
     */
    @SuppressWarnings("rawtypes")
    Class getClass(BoxResourceType type);

    ObjectMapper getObjectMapper();

}
