package com.questwork.myapplication.Model;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/1/26.
 */
public class AssetRepo extends RealmObject {
    private long timestamp;
    private String fullPath;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
