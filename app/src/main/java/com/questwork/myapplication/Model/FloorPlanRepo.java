package com.questwork.myapplication.Model;

import com.questwork.myapplication.GsonHelper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/1/26.
 */
public class FloorPlanRepo extends RealmObject {
    public static final String PARAM_FLOORPLAN = "id";
    public static final String PARAM_PARENT_ID = "parentId";
    @PrimaryKey
    private String id;
    private String name;
    private AssetRepo assetRepo;
    private String parentId;

    public AssetRepo getAssetRepo() {
        return assetRepo;
    }

    public void setAssetRepo(AssetRepo assetRepo) {
        this.assetRepo = assetRepo;
    }



    public static FloorPlanRepo save(FloorPlanRepo repository) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FloorPlanRepo FloorPlanRepo = realm.copyToRealmOrUpdate(repository);
        realm.commitTransaction();
        return FloorPlanRepo;
    }

    public static FloorPlanRepo save(String json) {
        return save(GsonHelper.fromJson(json, FloorPlanRepo.class));
    }

    public static List<FloorPlanRepo> findAll() {
        return Realm.getDefaultInstance().where(FloorPlanRepo.class).findAll();
    }

    public static List<FloorPlanRepo> findByParent(String parentId) {
        return Realm.getDefaultInstance().where(FloorPlanRepo.class).equalTo(PARAM_PARENT_ID, parentId).findAll();
    }

    public static FloorPlanRepo findByFloorPlanId(String id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(FloorPlanRepo.class).equalTo(PARAM_FLOORPLAN, id).findFirst();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
