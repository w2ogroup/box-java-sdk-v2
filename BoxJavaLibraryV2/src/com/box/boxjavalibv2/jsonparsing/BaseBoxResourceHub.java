package com.box.boxjavalibv2.jsonparsing;

import com.box.boxjavalibv2.dao.BoxObject;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.interfaces.IBoxType;

public abstract class BaseBoxResourceHub implements IBoxResourceHub {

    public BaseBoxResourceHub() {
        initializeTypes();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getClass(IBoxType type) {
        return BoxObject.class;
    }

    /**
     * Get the concrete class for IBoxType
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected abstract Class getConcreteClassForIBoxType();

    /**
     * Get class for a certain type, assuming the input type is an object of the concrete class of IBoxType defined in this resource hub.
     * 
     * @param <T>
     * 
     * @param type
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected abstract Class getObjectClassGivenConcreteIBoxType(IBoxType type);

    protected abstract void initializeTypes();
}
