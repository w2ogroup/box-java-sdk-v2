package com.box.boxjavalibv2.interfaces;

/**
 * Interface for storage to save authentication(OAuth) objects in a secure way.
 */
public interface IAuthSecureStorage {

    /**
     * Save auth in a secure way.
     * 
     * @param auth
     */
    void saveAuth(IAuthData auth);

    /**
     * Get auth.
     * 
     * @return
     */
    IAuthData getAuth();
}
