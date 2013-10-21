package com.box.boxjavalibv2.interfaces;

import java.io.InputStream;

import com.box.restclientv2.exceptions.BoxRestException;

public interface IBoxJSONParser {

    // TODO: throw different exception.
    String convertIJSONStringEntitytoString(final Object object) throws BoxRestException;

    <T> T parseJSONStringIntoObject(final InputStream inputStream, final Class<T> theClass);

    <T> T parseJSONStringIntoObject(final String jsonString, final Class<T> theClass);
}
