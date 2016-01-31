package com.questwork.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.questwork.myapplication.Model.FloorPlan;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    //    Button requestButton;
//    Button readButton;
//    Button writeButton;
    List<FloorPlan> floorPlanList;
    @Bind(R.id.responseTextView)
    TextView responseTextView;
    @Bind(R.id.requestButton)
    Button requestButton;
    @Bind(R.id.readButton)
    Button readButton;
    @Bind(R.id.dataTextView)
    TextView dataTextView;
    @Bind(R.id.writeButton)
    Button writeButton;
    @Bind(R.id.ipEditText)
    EditText ipEditText;
    @Bind(R.id.ipbutton)
    Button ipbutton;
    private String ip;
    private ApiService service;
    private Retrofit retrofit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        ButterKnife.bind(this);
        ip = "http://" + ipEditText.getText() + ":8028";
        floorPlanList = new ArrayList<>();


    }


    @OnClick(R.id.requestButton)
    public void request() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ip)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
        Call<List<FloorPlan>> floorPlans = service.listFloorPlan();
        floorPlans.enqueue(new Callback<List<FloorPlan>>() {
            @Override
            public void onResponse(Response<List<FloorPlan>> response) {
                floorPlanList.addAll(response.body());
                String str = "";
                for (FloorPlan floorPlan : floorPlanList) {
                    str += floorPlan.toJson() + "\n";
                }
                responseTextView.setText(str);
                Log.i("aaaq", "success");
            }

            @Override
            public void onFailure(Throwable t) {
                responseTextView.setText("check your ip address");
                Log.i("aaaq", t.getMessage());
            }
        });
    }
@OnClick(R.id.ipbutton)
public void setIp(){
   ip = "http://" + ipEditText.getText() + ":8028";
}

    //
    @OnClick(R.id.writeButton)
    public void write(View v) {
        Log.i("aaaq", "button");
        for (FloorPlan floorPlan : floorPlanList) {
            Log.i("aaaq", "before saving into realm DB\n" + floorPlan.toJson());
            FloorPlan ff = floorPlan.saveToRepository();
            Log.i("aaaq", "after saving into realm DB\n" + ff.toJson());
        }
    }

    @OnClick(R.id.readButton)
    public void read(View v) {
        List<FloorPlan> floorPlanList = FloorPlan.findAll();
        String str = "";
        for (FloorPlan floorPlan : floorPlanList) {
            str += floorPlan.toJson() + "\n";
        }
        dataTextView.setText(str);
        Log.i("aaaq", "read from DB\n" + str);
    }
}
