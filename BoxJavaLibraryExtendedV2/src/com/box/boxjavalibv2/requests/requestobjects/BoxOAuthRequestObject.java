package com.box.boxjavalibv2.requests.requestobjects;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.box.restclientv2.exceptions.BoxRestException;

public class BoxOAuthRequestObject extends BoxDefaultRequestObject {

    private final static String GRANT_TYPE = "grant_type";
    private final static String CODE = "code";
    private final static String CLIENT_ID = "client_id";
    private final static String CLIENT_SECRET = "client_secret";
    private final static String REDIRECT_URL = "redirect_url";
    private final static String REFRESH_TOKEN = "refresh_token";
    private final static String AUTHORIZATION_CODE = "authorization_code";

    private BoxOAuthRequestObject() {
    }

    /**
     * Request object to create OAUth.
     * 
     * @param code
     *            The authorization code you retrieved previously used to create OAuth.
     * @param clientId
     *            client id
     * @param clientSecret
     *            client secret
     * @param redirectUri
     *            optional, required only if a redirect URI is not configured at <a href="http://box.com/developers/services">Box Developers Services</a>, use
     *            null if don't want to supply this field.
     * @return BoxOAuthRequestObject
     */
    public static BoxOAuthRequestObject createOAuthRequestObject(final String code, final String clientId, final String clientSecret, final String redirectUrl) {
        return (new BoxOAuthRequestObject()).setAuthCode(code).setClient(clientId, clientSecret).setRedirectUrl(redirectUrl);
    }

    public static BoxOAuthRequestObject refreshOAuthRequestObject(final String refreshToken, final String clientId, final String clientSecret) {
        return (new BoxOAuthRequestObject()).setRefreshToken(refreshToken).setClient(clientId, clientSecret);
    }

    public BoxOAuthRequestObject setRefreshToken(final String refreshToken) {
        put(GRANT_TYPE, REFRESH_TOKEN);
        put(REFRESH_TOKEN, refreshToken);
        return this;
    }

    /**
     * @param code
     *            The authorization code you retrieved previously used to create OAuth.
     * @return
     */
    public BoxOAuthRequestObject setAuthCode(String code) {
        put(GRANT_TYPE, AUTHORIZATION_CODE);
        put(CODE, code);
        return this;
    }

    public BoxOAuthRequestObject setClient(String clientId, String clientSecret) {
        put(CLIENT_ID, clientId);
        put(CLIENT_SECRET, clientSecret);
        return this;
    }

    public BoxOAuthRequestObject setRedirectUrl(String redirectUrl) {
        put(REDIRECT_URL, redirectUrl);
        return this;
    }

    @Override
    public UrlEncodedFormEntity getEntity() throws BoxRestException {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : getJSONEntity().entrySet()) {
            Object value = entry.getValue();
            if (value != null && value instanceof String) {
                pairs.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
            }
        }

        try {
            return new UrlEncodedFormEntity(pairs, CharEncoding.UTF_8);
        }
        catch (UnsupportedEncodingException e) {
            throw new BoxRestException(e);
        }
    }
}
