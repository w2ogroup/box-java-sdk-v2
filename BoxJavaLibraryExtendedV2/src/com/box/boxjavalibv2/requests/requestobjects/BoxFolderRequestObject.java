package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxEmail;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;
import com.box.boxjavalibv2.utils.Constants;

public class BoxFolderRequestObject extends BoxItemRequestObject {

    private BoxFolderRequestObject() {
    }

    public static BoxFolderRequestObject deleteSharedLinkRequestObject() {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setSharedLink(null);
    }

    public static BoxFolderRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestObject sharedLink) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setSharedLink(sharedLink);
    }

    public static BoxFolderRequestObject createFolderRequestObject(String name, String parentId) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setName(name).setParent(parentId);
    }

    public static BoxFolderRequestObject deleteFolderRequestObject(boolean recursive) {
        return (new BoxFolderRequestObject()).setRecursive(recursive);
    }

    /**
     * BoxFolderRequestObject for get folder items request.
     * 
     * @param limit
     *            the number of items to return. default is 100, max is 1000.
     * @param offset
     *            the item at which to begin the response, default is 0.
     * @return BoxFolderRequestObject
     */
    public static BoxFolderRequestObject getFolderItemsRequestObject(final int limit, final int offset) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setPage(limit, offset);
    }

    /**
     * BoxFolderRequestObject for copy folder request.
     * 
     * @param parentId
     *            id of destination parent folder.
     * @return BoxFolderRequestObject
     */
    public static BoxFolderRequestObject copyFolderRequestObject(String parentId) {
        return (BoxFolderRequestObject) (new BoxFolderRequestObject()).setParent(parentId);
    }

    public static BoxFolderRequestObject updateFolderInfoRequestObject() {
        return (new BoxFolderRequestObject());
    }

    /**
     * Set whether operation is done recursively. (For example deleting a folder)
     * 
     * @param recursive
     * @return
     */
    public BoxFolderRequestObject setRecursive(final boolean recursive) {
        addQueryParam(Constants.RECURSIVE, Boolean.toString(recursive));
        return this;
    }

    /**
     * Set the email-to-upload address for this folder.
     * 
     * @param access
     *            access level
     * @param email
     *            email address
     * @return
     */
    public BoxFolderRequestObject setUploadEmail(String access, String email) {
        MapJSONStringEntity entity = new MapJSONStringEntity();
        entity.put(BoxEmail.FIELD_ACCESS, access);
        entity.put(BoxEmail.FIELD_EMAIL, email);
        put(BoxFolder.FIELD_FOLDER_UPLOAD_EMAIL, entity);
        return this;
    }
}
