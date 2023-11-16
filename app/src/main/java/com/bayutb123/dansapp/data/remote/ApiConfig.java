package com.bayutb123.dansapp.data.remote;

import retrofit2.Retrofit;

public class ApiConfig {
    public static final String BASE_URL = "https://api.github.com/";

    public static ApiService getApiService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService.class);
    }
}
