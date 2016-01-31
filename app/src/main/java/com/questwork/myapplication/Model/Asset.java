package com.questwork.myapplication.Model;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/1/23.
 */
public class Asset{
    private long timestamp;
    private String fullPath;

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Asset() {
    }

    public static Asset objectTranslate(AssetRepo repo){
        if (repo == null)
            return null;
        Asset asset = new Asset();
        asset.setFullPath(repo.getFullPath());
        asset.setTimestamp(repo.getTimestamp());
        return asset;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
