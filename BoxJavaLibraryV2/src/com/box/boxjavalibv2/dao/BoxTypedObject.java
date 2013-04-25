package com.box.boxjavalibv2.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.box.boxjavalibv2.interfaces.IBoxParcelWrapper;
import com.box.boxjavalibv2.utils.ISO8601DateParser;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is base class for all box objects.
 */
public class BoxTypedObject extends BoxObject {

    public static final String FIELD_TYPE = "type";
    public static final String FIELD_ID = "id";
    public static final String FIELD_CREATED_AT = "created_at";
    public static final String FIELD_MODIFIED_AT = "modified_at";

    public BoxTypedObject() {
    }

    /**
     * Copy constructor, this does deep copy for all the fields.
     * 
     * @param obj
     */
    public BoxTypedObject(BoxTypedObject obj) {
        super(obj);
    }

    /**
     * Instantiate the object from a map. Each entry in the map reflects to a field.
     * 
     * @param map
     */
    public BoxTypedObject(Map<String, Object> map) {
        super(map);
    }

    /**
     * Get BoxResourceType of this object.
     * 
     * @return
     */
    public BoxResourceType resourceType() {
        return BoxResourceType.valueOf(getType().toUpperCase(Locale.ENGLISH));
    }

    /**
     * Get the type.
     * 
     * @return type
     */
    @JsonProperty(FIELD_TYPE)
    public String getType() {
        return (String) getValue(FIELD_TYPE);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param type
     *            type
     */
    @JsonProperty(FIELD_TYPE)
    public void setType(String type) {
        put(FIELD_TYPE, type);
    }

    /**
     * Get id.
     * 
     * @return id
     */
    @JsonProperty(FIELD_ID)
    public String getId() {
        return (String) getValue(FIELD_ID);
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param id
     *            id
     */
    @JsonProperty(FIELD_ID)
    private void setId(String id) {
        put(FIELD_ID, id);
    }

    /**
     * Get the time this user was created at. (This returns a String and can be parsed into {@link java.util.Date} by
     * {@link com.box.boxjavalibv2.utils.ISO8601DateParser})
     * 
     * @return the created_at
     */
    @JsonProperty(FIELD_CREATED_AT)
    public String getCreatedAt() {
        return (String) getValue(FIELD_CREATED_AT);
    }

    /**
     * Get the time created at.
     * 
     * @return Date representation of the created_at value. Null if there was no created_at or if it could not be parsed as an ISO8601 date.
     * @throws ParseException
     */
    public Date dateCreatedAt() {
        String dateCreatedAt = getCreatedAt();
        try {
            Date date = StringUtils.isEmpty(dateCreatedAt) ? null : ISO8601DateParser.parse(dateCreatedAt);
            return date;
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param createdAt
     *            the created_at to set
     */
    @JsonProperty(FIELD_CREATED_AT)
    private void setCreatedAt(String createdAt) {
        put(FIELD_CREATED_AT, createdAt);
    }

    /**
     * Get the time this user was modified the last time. (This returns a String and can be parsed into {@link java.util.Date} by
     * {@link com.box.boxjavalibv2.utils.ISO8601DateParser})
     * 
     * @return the modified_at
     */
    @JsonProperty(FIELD_MODIFIED_AT)
    public String getModifiedAt() {
        return (String) getValue(FIELD_MODIFIED_AT);
    }

    /**
     * Get the date this object is modified at.
     * 
     * @return Date representation of the modified_at value. Null if there was no date_modified or if it could not be parsed as an ISO8601 date.
     * @throws ParseException
     */
    public Date dateModifiedAt() {
        String dateModifiedAt = getModifiedAt();
        try {
            Date date = StringUtils.isEmpty(dateModifiedAt) ? null : ISO8601DateParser.parse(dateModifiedAt);
            return date;
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * Setter. This is only used by {@see <a href="http://jackson.codehaus.org">Jackson JSON processer</a>}
     * 
     * @param modifiedAt
     *            the modified_at to set
     */
    @JsonProperty(FIELD_MODIFIED_AT)
    private void setModifiedAt(String modifiedAt) {
        put(FIELD_MODIFIED_AT, modifiedAt);
    }

    public BoxTypedObject(IBoxParcelWrapper in) {
        super(in);
    }
}
