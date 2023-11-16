package com.bayutb123.dansapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bayutb123.dansapp.databinding.ActivityMainBinding;
import com.bayutb123.dansapp.ui.adapter.RecyclerAdapter;
import com.bayutb123.dansapp.ui.login.LoginActivity;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private AtomicBoolean isNoMoreData = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkLogin();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.fetchJobs("", "", false, 1);

        binding.jobList.setLayoutManager(new LinearLayoutManager(this));
        binding.jobList.setItemAnimator(new DefaultItemAnimator());

        setupPagination();
        mainViewModel.isNoMoreData.observe(this, bool -> {
            isNoMoreData.set(bool);
            if (isNoMoreData.get()) {
                Toast.makeText(this, "No more data", Toast.LENGTH_SHORT).show();
            }
        });

        mainViewModel.jobsLiveData.observe(this, jobs -> {
            RecyclerAdapter adapter = new RecyclerAdapter(jobs);
            binding.jobList.setAdapter(adapter);
        });

        mainViewModel.isLoadingLiveData.observe(this, this::setLoading);

        binding.applyFilter.setOnClickListener(view -> filter());
        binding.filterButton.setOnClickListener(view -> {
            if (binding.filterLayout.getVisibility() == View.GONE) {
                binding.filterLayout.setVisibility(View.VISIBLE);
            } else {
                binding.filterLayout.setVisibility(View.GONE);
            }
        });

        binding.searchJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void setupPagination() {
        Log.d("MainActivity", "isNoMoreData: " + isNoMoreData);
        binding.jobList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisible = layoutManager.findLastVisibleItemPosition();
                    boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
                    if (totalItemCount > 0 && endHasBeenReached && !isNoMoreData.get()) {
                        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                        mainViewModel.fetchNextPage("", "", false, totalItemCount / 10 + 1);
                    }
                }
            }
        });
    }

    private void checkLogin() {
        boolean login = getIntent().getBooleanExtra("login", false);
        if (!login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void filter() {
        boolean isFullTime = binding.fullTimeSwitch.isChecked();
        String description = binding.searchJob.getText().toString();
        String location = binding.filterLocation.getText().toString();
        mainViewModel.filter(description, location, isFullTime);
        binding.filterLayout.setVisibility(View.GONE);
    }

    public void setLoading(boolean loading) {
        if (loading) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.jobList.setVisibility(View.GONE);
            Log.d("MainActivity", "Loading...");
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.jobList.setVisibility(View.VISIBLE);
            Log.d("MainActivity", "Done");
        }
    }
}