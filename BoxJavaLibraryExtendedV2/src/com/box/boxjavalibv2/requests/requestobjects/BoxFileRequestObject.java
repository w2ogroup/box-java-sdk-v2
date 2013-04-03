package com.box.boxjavalibv2.requests.requestobjects;

public class BoxFileRequestObject extends BoxItemRequestObject {

    private BoxFileRequestObject() {
    }

    /**
     * BoxFileRequestObject for delete file request.
     * 
     * @return BoxFileRequestObject
     */
    public static BoxFileRequestObject deleteFileRequestObject() {
        return new BoxFileRequestObject();
    }

    /**
     * BoxFileRequestObject for copy file request.
     * 
     * @param parentId
     *            id of destination parent folder.
     * @return
     */
    public static BoxFileRequestObject copyFileRequestObject(String parentId) {
        return (BoxFileRequestObject) (new BoxFileRequestObject()).setParent(parentId);
    }

    public static BoxFileRequestObject deleteSharedLinkRequestObject() {
        return (BoxFileRequestObject) (new BoxFileRequestObject()).setSharedLink(null);
    }

    public static BoxFileRequestObject createSharedLinkRequestObject(BoxSharedLinkRequestObject sharedLinkObject) {
        return (BoxFileRequestObject) (new BoxFileRequestObject()).setSharedLink(sharedLinkObject);
    }

    public static BoxFileRequestObject updateFileRequestObject() {
        return new BoxFileRequestObject();
    }
}
