package com.bayutb123.dansapp.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.bayutb123.dansapp.R;
import com.bayutb123.dansapp.ui.adapter.RecyclerAdapter;
import com.bayutb123.dansapp.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bayutb123.dansapp.databinding.ActivityMainBinding;

import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        boolean login = getIntent().getBooleanExtra("login", false);
        if (!login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.fetchJobs();

        binding.jobList.setHasFixedSize(true);
        binding.jobList.setLayoutManager(new LinearLayoutManager(this));

        mainViewModel.jobsLiveData.observe(this, jobs -> {
            Log.d("MainActivity", "All JObs: " + jobs.size());
            RecyclerAdapter adapter = new RecyclerAdapter(jobs);
            binding.jobList.setAdapter(adapter);
        });

        binding.applyFilter.setOnClickListener(view -> {
            filter();
        });
        binding.filterButton.setOnClickListener(view -> {
            if (binding.filterLayout.getVisibility() == View.GONE) {
                binding.filterLayout.setVisibility(View.VISIBLE);
            } else {
                binding.filterLayout.setVisibility(View.GONE);
            }
        });
    }

    public void filter() {
        binding.filterLayout.setVisibility(View.GONE);
    }
}