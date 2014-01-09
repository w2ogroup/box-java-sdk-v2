package com.box.boxjavalibv2.dao;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.box.boxjavalibv2.interfaces.IBoxType;

/**
 * Type of resources that can be requested by API's.
 */
public enum BoxResourceType implements IBoxType {
    /*** An item, which could be a file, a folder, a weblink... */
    ITEM,
    /** A plural format of {@link #ITEM}. */
    ITEMS,
    /** A box file. */
    FILE,
    /** A plural format of {@link #FILE}. */
    FILES,
    /** A box weblink. */
    WEB_LINK,
    /** A plural format of {@link #WEB_LINK}. */
    WEB_LINKS,
    /** Preview of a file. */
    PREVIEW,
    /** A box folder. */
    FOLDER,
    /** A box user. */
    USER,
    /** A plural format of {@link #USER}. */
    USERS,
    /** A group of {@link #GROUPS}. */
    GROUP,
    /** A comment. */
    COMMENT,
    /** A plural format of {@link #COMMENT}. */
    COMMENTS,
    /** A version of a file. */
    FILE_VERSION,
    /** A plural format of {@link #FILE_VERSION}. */
    FILE_VERSIONS,
    /** Box's equivalent of access control lists. They can be used to set and apply permissions for users to folders. */
    COLLABORATION,
    /** A plural format of {@link ResourceType.COLLABORATION}. */
    COLLABORATIONS,
    /** An email alias. */
    EMAIL_ALIAS,
    /** A plural format of {@link #EMAIL_ALIAS}. */
    EMAIL_ALIASES,
    /** OAuth data. */
    OAUTH_DATA,
    /** Error. */
    ERROR,
    /** Event. */
    EVENT,
    /** A plural format of {@link #EVENT}. */
    EVENTS,
    /** Updates */
    UPDATES,
    /** Realtime server. */
    REALTIME_SERVER,
    /** File lock (shows up in event stream). */
    LOCK,
    /** Service action is a subtype of file lock. */
    SERVICE_ACTION,
    /** Administrator settings */
    ADMIN_SETTINGS,
    /** Login token */
    LOGIN_TOKEN;

    // As a performance optimization, set up string values for all types.
    private static final Map<BoxResourceType, String> typeToLowercaseString = new HashMap<BoxResourceType, String>();
    private static final Map<String, BoxResourceType> lowercaseStringToType = new HashMap<String, BoxResourceType>();
    static {
        for (BoxResourceType type : values()) {
            String str = type.name().toLowerCase(Locale.ENGLISH);
            typeToLowercaseString.put(type, str);
            lowercaseStringToType.put(str, type);
        }
    }

    @Override
    public String toString() {
        return typeToLowercaseString.get(this);
    }

    /**
     * Get the String representing plural format of a resource.
     * 
     * @return the String representing plural format of a resource
     */
    public String toPluralString() {
        return toString() + "s";
    }

    /**
     * Get the BoxResourceType from a lower case string value. For example "file" would return BoxResourceType.FILE
     * 
     * @param string
     * @return
     */
    public static BoxResourceType getTypeFromLowercaseString(final String string) {
        return lowercaseStringToType.get(string);
    }
}
