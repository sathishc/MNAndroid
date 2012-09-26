package com.alcorsys.medianearby.models;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class OwnershipInstance {
    private long id;
    private String mediaItemIdentifier;
    private String name;
    private String type;
    private String userId;
    private String userThumbnail;
    private String thumbnail;
    private String genre;
    private String ownerName;

    public HashMap<String,String> toHashMap(){
        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("id",String.valueOf(id));
        hashMap.put("mediaItemIdentifier",mediaItemIdentifier);
        hashMap.put("name",name);
        hashMap.put("type",type);
        hashMap.put("userId",userId);
        hashMap.put("userThumbnail",userThumbnail);
        hashMap.put("thumbnail",thumbnail);
        hashMap.put("genre",genre);
        hashMap.put("ownerName",ownerName);
        return hashMap;
    }

    public OwnershipInstance(){
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMediaItemIdentifier() {
        return mediaItemIdentifier;
    }

    public void setMediaItemIdentifier(String mediaItemIdentifier) {
        this.mediaItemIdentifier = mediaItemIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserThumbnail() {
        return userThumbnail;
    }

    public void setUserThumbnail(String userThumbnail) {
        this.userThumbnail = userThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
