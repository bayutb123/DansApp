package com.bayutb123.dansapp.data.remote;

import com.bayutb123.dansapp.data.response.PositionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/recruitment/positions.json")
    Call<PositionsResponse> getUsers();

}
