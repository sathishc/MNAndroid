package com.alcorsys.medianearby.models;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShelfListInstance {
    private long ownershipId;
    private String genre;
    private String mediaItemIdentifier;
    private String mediaItemName;
    private String mediaItemThumbnail;
    private String mediaItemType;
    private String mediaDescription;



    public ShelfListInstance(){
        super();
    }


    public long getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(long ownershipId) {
        this.ownershipId = ownershipId;
    }

    public String getMediaItemIdentifier() {
        return mediaItemIdentifier;
    }

    public void setMediaItemIdentifier(String mediaItemIdentifier) {
        this.mediaItemIdentifier = mediaItemIdentifier;
    }

    public String getMediaItemName() {
        return mediaItemName;
    }

    public void setMediaItemName(String mediaItemName) {
        this.mediaItemName = mediaItemName;
    }

    public String getMediaItemThumbnail() {
        return mediaItemThumbnail;
    }

    public void setMediaItemThumbnail(String mediaItemThumbnail) {
        this.mediaItemThumbnail = mediaItemThumbnail;
    }

    public String getMediaItemType() {
        return mediaItemType;
    }

    public void setMediaItemType(String mediaItemType) {
        this.mediaItemType = mediaItemType;
    }

    public String getMediaDescription() {
        return mediaDescription;
    }

    public void setMediaDescription(String mediaDescription) {
        this.mediaDescription = mediaDescription;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
