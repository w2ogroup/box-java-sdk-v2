package com.box.restclientv2;

/**
 * REST Method.
 */
public enum RestMethod {
    GET, PUT, POST, DELETE, OTHERS;

    private static final String METHOD_GET = "get";
    private static final String METHOD_PUT = "put";
    private static final String METHOD_POST = "post";
    private static final String METHOD_DELETE = "delete";

    /**
     * Get String
     * 
     * @return string.
     */
    public String getMethodString() {
        switch (this) {
            case GET:
                return METHOD_GET;
            case PUT:
                return METHOD_PUT;
            case POST:
                return METHOD_POST;
            case DELETE:
                return METHOD_DELETE;
            default:
                break;
        }
        return null;
    }
}
