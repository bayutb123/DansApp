package com.bayutb123.dansapp.ui.detail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bayutb123.dansapp.data.remote.ApiConfig;
import com.bayutb123.dansapp.data.remote.ApiService;
import com.bayutb123.dansapp.model.Jobs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    ApiService apiService = ApiConfig.getApiService();
    private static final String TAG = "DetailViewModel";

    private MutableLiveData<Jobs> _jobsDetail = new MutableLiveData<>();
    public MutableLiveData<Jobs> jobsDetail = _jobsDetail;

    public void setJobsDetail(String id) {
        try {
            apiService.getJobDetail(id).enqueue(new Callback<Jobs>() {
                @Override
                public void onResponse(Call<Jobs> call, Response<Jobs> response) {
                    Jobs jobs = response.body();
                    if (jobs != null) {
                        _jobsDetail.postValue(jobs);
                    }
                }

                @Override
                public void onFailure(Call<Jobs> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "setJobsDetail: " + e.getMessage());
        }
    }
}
