package com.box.boxjavalibv2.dao;

/**
 * Type of resources that can be requested by API's.
 */
public enum BoxResourceType {
    /*** An item, which could be a file, a folder, a weblink... */
    ITEM,
    /** A plural format of {@link #ITEM}. */
    ITEMS,
    /** A box file. */
    FILE,
    /** A plural format of {@link #FILE}. */
    FILES,
    /** A box weblink.*/
    WEB_LINK,
    /**A plural format of {@link #WEB_LINK}.*/
    WEB_LINKS,
    /** Preview of a file. */
    PREVIEW,
    /** A box folder. */
    FOLDER,
    /** A box user. */
    USER,
    /** A plural format of {@link #USER}. */
    USERS,
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
    ERROR;

    @Override
    public String toString() {
        switch (this) {
            default:
                return super.toString().toLowerCase();
        }
    }

    /**
     * Get the String representing plural format of a resource.
     * 
     * @return the String representing plural format of a resource
     */
    public String toPluralString() {
        return toString() + "s";
    }
}
