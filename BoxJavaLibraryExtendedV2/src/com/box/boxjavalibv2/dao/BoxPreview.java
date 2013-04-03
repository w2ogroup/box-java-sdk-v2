package com.box.boxjavalibv2.dao;

import java.io.InputStream;

import com.box.boxjavalibv2.interfaces.IBoxParcelWrapper;
import com.box.restclientv2.exceptions.BoxRestException;

/**
 * Preview of a file.
 */
public class BoxPreview extends BoxObject {

    public final static String MIN_WIDTH = "min_width";
    public final static String MIN_HEIGHT = "min_height";
    public final static String MAX_WIDTH = "max_width";
    public final static String MAX_HEIGHT = "max_height";
    public final static String PAGE = "page";

    private int firstPage = 1;
    private int lastPage = 1;

    private InputStream content;

    /**
     * @param firstPage
     *            first page number
     */
    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    /**
     * Get the first page number.
     * 
     * @return the first page number.
     */
    public Integer getFirstPage() {
        return this.firstPage;
    }

    /**
     * Set the last page number.
     * 
     * @param lastPage
     *            last page number
     */
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    /**
     * Get the last page number.
     * 
     * @return the last page number
     */
    public Integer getLastPage() {
        return this.lastPage;
    }

    /**
     * Get content of the preview. Caller is responsible for closing the InputStream.
     * 
     * @return preview input stream.
     * @throws BoxException
     */
    public InputStream getContent() throws BoxRestException {
        return content;
    }

    /**
     * Get number of pages.
     * 
     * @return number of pages
     */
    public Integer getNumPages() {
        return getLastPage() - getFirstPage() + 1;
    }

    /**
     * Set content.
     * 
     * @param content
     *            content
     */
    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public void writeToParcel(IBoxParcelWrapper parcelWrapper, int flags) {
        throw new UnsupportedOperationException("Writing Preview to parcel is not supported!");
    }
}
