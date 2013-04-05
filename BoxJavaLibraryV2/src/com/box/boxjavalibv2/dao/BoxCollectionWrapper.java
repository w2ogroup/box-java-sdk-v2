package com.box.boxjavalibv2.dao;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Collection of box items(files, folders, web links...), this is a wrapper of {@link BoxCollection}
 */
public class BoxCollectionWrapper extends BoxObject {

    public final static String FIELD_ITEM_COLLECTION = "item_collection";

    public BoxCollectionWrapper() {
    }

    /**
     * Copy constructor, this does deep copy for all the fields.
     * 
     * @param obj
     */
    public BoxCollectionWrapper(BoxCollectionWrapper obj) {
        super(obj);
    }

    /**
     * Instantiate the object from a map. Each entry in the map reflects to a field.
     * 
     * @param map
     */
    public BoxCollectionWrapper(Map<String, Object> map) {
        super(map);
    }

    /**
     * Getter.
     * 
     * @return collection of the box items.
     */
    @JsonProperty("item_collection")
    public BoxCollection getItemCollection() {
        return (BoxCollection) getValue(FIELD_ITEM_COLLECTION);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param itemCollection
     *            item collection
     */
    @JsonProperty("item_collection")
    private void setItemCollection(BoxCollection itemCollection) {
        put(FIELD_ITEM_COLLECTION, itemCollection);
    }
}
