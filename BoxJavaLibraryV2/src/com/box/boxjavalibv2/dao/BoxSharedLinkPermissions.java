package com.box.boxjavalibv2.dao;

import java.util.Map;

import com.box.boxjavalibv2.interfaces.IBoxParcelWrapper;

// CHECKSTYLE:OFF
/**
 * Permission for shared links.
 * 
 */
public class BoxSharedLinkPermissions extends BoxObject {

    private boolean can_download;
    private boolean can_preview;

    /**
     * Default constructor.
     */
    public BoxSharedLinkPermissions() {
    }

    /**
     * Copy constructor, this does deep copy for all the fields.
     * 
     * @param obj
     */
    public BoxSharedLinkPermissions(BoxSharedLinkPermissions obj) {
        super(obj);
    }

    /**
     * Instantiate the object from a map. Each entry in the map reflects to a field.
     * 
     * @param map
     */
    public BoxSharedLinkPermissions(Map<String, Object> map) {
        super(map);
    }

    /**
     * Constructor.
     * 
     * @param canDownload
     *            can be downloaded
     * @param canPreview
     *            can be previewed
     */
    public BoxSharedLinkPermissions(final boolean canDownload, final boolean canPreview) {
        this.setCan_download(canDownload);
        this.setCan_preview(canPreview);
    }

    /**
     * whether can_download is true.
     * 
     * @return can_download
     */
    public boolean isCan_download() {
        return can_download;
    }

    /**
     * Setter.
     * 
     * @param canDownload
     */
    private void setCan_download(final boolean canDownload) {
        this.can_download = canDownload;
    }

    /**
     * whether can_preview is true.
     * 
     * @return can_preview
     */
    public boolean isCan_preview() {
        return can_preview;
    }

    /**
     * Setter.
     * 
     * @param canPreview
     */
    private void setCan_preview(final boolean canPreview) {
        this.can_preview = canPreview;
    }

    protected BoxSharedLinkPermissions(IBoxParcelWrapper parcel) {
        super(parcel);
    }
}
