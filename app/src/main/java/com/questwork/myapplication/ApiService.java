package com.questwork.myapplication;

import com.questwork.myapplication.Model.FloorPlan;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/1/23.
 */
public interface ApiService {
    @GET("questcms/floorplan")
    Call<List<FloorPlan>> listFloorPlan();
}
