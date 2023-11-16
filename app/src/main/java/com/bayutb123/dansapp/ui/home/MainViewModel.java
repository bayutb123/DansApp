package com.bayutb123.dansapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bayutb123.dansapp.data.Repository;
import com.bayutb123.dansapp.data.remote.ApiConfig;
import com.bayutb123.dansapp.data.remote.ApiService;
import com.bayutb123.dansapp.model.Jobs;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private ApiService apiService = ApiConfig.getApiService();
    private static final String TAG = "MainViewModel";

    // LiveData
    private MutableLiveData<List<Jobs>> _jobsLiveData = new MutableLiveData<>();
    LiveData<List<Jobs>> jobsLiveData = _jobsLiveData;

    public void fetchJobs() {
            try {
                apiService.getJobs().enqueue(new Callback<List<Jobs>>() {
                    @Override
                    public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                        List<Jobs> jobs = response.body();
                        if (jobs != null) {
                            _jobsLiveData.postValue(jobs);
                        }
                        Log.d(TAG, "onResponse: " + jobs.get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Call<List<Jobs>> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, "Error fetching jobs: " + e.getMessage());
                // Handle error, misalnya, memposting LiveData dengan nilai null atau pesan error
                _jobsLiveData.postValue(null);
            }
    }

}
