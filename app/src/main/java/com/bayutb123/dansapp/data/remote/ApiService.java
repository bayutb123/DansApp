package com.bayutb123.dansapp.data.remote;

import com.bayutb123.dansapp.model.Jobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("recruitment/positions.json")
    Call<List<Jobs>> getJobs(
            @Query("description") String description,
            @Query("location") String location,
            @Query("full_time") boolean isFullTime,
            @Query("page") int page
    );

    @GET("recruitment/positions.json")
    Call<List<Jobs>> searchJob(
            @Query("description") String description,
            @Query("location") String location,
            @Query("full_time") boolean isFullTime
    );

    @GET("recruitment/positions/{id}")
    Call<Jobs> getJobDetail(
            @Path("id") String id
    );

}
