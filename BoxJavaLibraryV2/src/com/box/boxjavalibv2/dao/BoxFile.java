package com.box.boxjavalibv2.dao;

import java.util.Map;

import com.box.boxjavalibv2.interfaces.IBoxParcelWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Box File object
 */
public class BoxFile extends BoxItem {

    public final static String FIELD_SHA1 = "sha1";

    /**
     * Constructor.
     */
    public BoxFile() {
        setType(BoxResourceType.FILE.toString());
    }

    /**
     * Copy constructor, this does deep copy for all the fields.
     * 
     * @param obj
     */
    public BoxFile(BoxFile obj) {
        super(obj);
    }

    /**
     * Instantiate the object from a map. Each entry in the map reflects to a field.
     * 
     * @param map
     */
    public BoxFile(Map<String, Object> map) {
        super(map);
    }

    /**
     * Get sha1 of the file.
     * 
     * @return sha1 of the file.
     */
    @JsonProperty(FIELD_SHA1)
    public String getSha1() {
        return (String) getValue(FIELD_SHA1);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param sha1
     *            sha1
     */
    @JsonProperty(FIELD_SHA1)
    private void setSha1(String sha1) {
        put(FIELD_SHA1, sha1);
    }

    public BoxFile(IBoxParcelWrapper in) {
        super(in);
    }
}
