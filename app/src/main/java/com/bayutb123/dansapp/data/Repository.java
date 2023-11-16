package com.bayutb123.dansapp.data;

import android.util.Log;

import com.bayutb123.dansapp.data.remote.ApiConfig;
import com.bayutb123.dansapp.data.remote.ApiService;
import com.bayutb123.dansapp.model.Jobs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Repository {
    private static final String TAG = "Repository";
    ApiService apiService = ApiConfig.getApiService();

    public List<Jobs> getJobs() {
        ArrayList<Jobs> jobsList = new ArrayList<>();

        return jobsList;
    }

}
