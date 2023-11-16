package com.bayutb123.dansapp.data.remote;

import com.bayutb123.dansapp.model.Jobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("recruitment/positions.json")
        Call<List<Jobs>> getJobs();

}
