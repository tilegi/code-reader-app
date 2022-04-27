package com.tilegi.codereader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfoList extends AppCompatActivity {
    List<InfoModel> infoModelList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        recyclerView = findViewById(R.id.recyclerView);
        infoModelList = new ArrayList<>();
        loadData();
        Collections.reverse(infoModelList);
        ItemAdapter adapter = new ItemAdapter(infoModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {
        Log.d("save",infoModelList.size()+"");
        SharedPreferences sharedPreferences = getSharedPreferences("ModelName",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(infoModelList);
        editor.putString("model list",json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("ModelName",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("model list",null);
        Type type= new TypeToken<ArrayList<InfoModel>>(){}.getType();
        infoModelList=gson.fromJson(json,type);
        Log.d("load",infoModelList.size()+"");
        if (infoModelList==null){
            infoModelList=new ArrayList<>();
        }

    }

}