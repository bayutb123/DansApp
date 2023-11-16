package com.bayutb123.dansapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bayutb123.dansapp.data.remote.ApiConfig;
import com.bayutb123.dansapp.data.remote.ApiService;
import com.bayutb123.dansapp.model.Jobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private ApiService apiService = ApiConfig.getApiService();
    private static final String TAG = "MainViewModel";

    // LiveData
    private MutableLiveData<List<Jobs>> _jobsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> _isNoMoreData = new MutableLiveData<>();
    private MutableLiveData<Boolean> _isLoadingLiveData = new MutableLiveData<>();
    LiveData<List<Jobs>> jobsLiveData = _jobsLiveData;
    LiveData<Boolean> isLoadingLiveData = _isLoadingLiveData;
    LiveData<Boolean> isNoMoreData = _isNoMoreData;

    public void fetchJobs(
            String description,
            String location,
            boolean isFullTime,
            int page
    ) {
        _isLoadingLiveData.postValue(true);
        _isNoMoreData.postValue(false);
        try {
            apiService.getJobs(
                    description,
                    location,
                    isFullTime,
                    page
            ).enqueue(new Callback<List<Jobs>>() {
                @Override
                public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                    List<Jobs> jobs = response.body();
                    if (jobs != null) {
                        _jobsLiveData.postValue(jobs);
                        _isLoadingLiveData.postValue(false);

                    }
                    Log.d(TAG, "onResponse: " + jobs);
                }

                @Override
                public void onFailure(Call<List<Jobs>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            _jobsLiveData.postValue(null);
        } finally {
            _isLoadingLiveData.postValue(false);
        }
    }

    public void fetchNextPage(
            String description,
            String location,
            boolean isFullTime,
            int page
    ) {
        _isLoadingLiveData.postValue(true);
        List<Jobs> currentJobs = _jobsLiveData.getValue();
        try {
            apiService.getJobs(
                    description,
                    location,
                    isFullTime,
                    page
            ).enqueue(new Callback<List<Jobs>>() {
                @Override
                public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                    List<Jobs> jobs = response.body();

                    if (jobs == null || jobs.size() < 10) {
                        _isNoMoreData.postValue(true);
                        Log.d(TAG, "no data: " + _isNoMoreData.getValue());
                    }
                    if (jobs != null) {
                        assert currentJobs != null;
                        currentJobs.addAll(jobs);
                        _jobsLiveData.postValue(currentJobs);
                    }

                    Log.d(TAG, "onResponse: " + jobs);
                }

                @Override
                public void onFailure(Call<List<Jobs>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            _jobsLiveData.postValue(null);
        } finally {
            _isLoadingLiveData.postValue(false);
        }
    }

    public void search(String description, String location, boolean isFullTime) {
        _isLoadingLiveData.postValue(true);
        try {
            apiService.searchJob(
                    description,
                    location,
                    isFullTime
            ).enqueue(new Callback<List<Jobs>>() {
                @Override
                public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                    List<Jobs> jobs = response.body();
                    if (jobs != null) {
                        _jobsLiveData.postValue(jobs);
                        _isLoadingLiveData.postValue(false);
                    }
                    Log.d(TAG, "onResponse: " + jobs);
                }

                @Override
                public void onFailure(Call<List<Jobs>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            _jobsLiveData.postValue(null);
        } finally {
            _isLoadingLiveData.postValue(false);
        }
    }

    public void filter(String description, String location, boolean isFullTime) {
        search(description, location, isFullTime);
    }

}
