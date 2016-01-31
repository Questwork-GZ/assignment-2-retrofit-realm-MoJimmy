package com.questwork.myapplication.Model;

import com.questwork.myapplication.GsonHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FloorPlan implements JsonCriteria{
    @PrimaryKey
    private String id;
    private String name;
    private Asset asset;
    private String parentId;

    public FloorPlan saveToRepository() {
        return objectTranslate(FloorPlanRepo.save(this.toJson()));
    }

    public static FloorPlan objectTranslate(FloorPlanRepo repo) {
        FloorPlan floorPlan = new FloorPlan();
        floorPlan.setName(repo.getName());
        floorPlan.setId(repo.getId());
        floorPlan.setParentId(repo.getParentId());
        Asset asset = Asset.objectTranslate(repo.getAssetRepo());
        floorPlan.setAsset(asset);
        return floorPlan;
    }

    public static List<FloorPlan> listTranslate(List<FloorPlanRepo> repositories) {
        List<FloorPlan> floorPlans = new ArrayList<>();
        if (repositories != null) {
            for (FloorPlanRepo repository : repositories) {
                floorPlans.add(objectTranslate(repository));
            }
        }
        return floorPlans;
    }

    public static List<FloorPlan> findAll() {
        return listTranslate(FloorPlanRepo.findAll());
    }

    public static FloorPlan findByFloorPlanId(String floorPlanId) {
        return objectTranslate(FloorPlanRepo.findByFloorPlanId(floorPlanId));
    }

    public static List<FloorPlan> findByParent(String parent) {
        return listTranslate(FloorPlanRepo.findByParent(parent));
    }


    public FloorPlan() {
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


    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toJson() {
        return GsonHelper.toJson(this);
    }
}
