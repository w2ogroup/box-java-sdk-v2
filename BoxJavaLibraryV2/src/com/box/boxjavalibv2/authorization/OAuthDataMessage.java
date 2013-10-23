package com.box.boxjavalibv2.authorization;

import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.interfaces.IBoxJSONParser;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * A message for OAuthData.
 */
public class OAuthDataMessage extends StringMessage {

    public static final String OAUTH_DATA_MESSAGE_KEY = "oauth_data";

    private IBoxJSONParser mParser;

    /**
     * Constructor.
     * 
     * @param oauthData
     *            OAuthData
     * @throws BoxRestException
     *             excetption
     */
    public OAuthDataMessage(final BoxOAuthToken oauthData, IBoxJSONParser parser) throws BoxRestException {
        super(OAUTH_DATA_MESSAGE_KEY, oauthData.toJSONString(parser));
    }

    /**
     * Get the OAuthData out from the value(String).
     * 
     * @return OAuthData
     */
    @Override
    public BoxOAuthToken getData() {
        return mParser.parseIntoObject((String) super.getData(), BoxOAuthToken.class);
    }
}
