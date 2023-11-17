package com.bayutb123.dansapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.bayutb123.dansapp.R;
import com.bayutb123.dansapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    DetailViewModel detailViewModel;
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        String id = getIntent().getStringExtra("id");
        detailViewModel.setJobsDetail(id);

        detailViewModel.jobsDetail.observe(this, jobs -> Log.d("DetailActivity", "onCreate: " + jobs));
    }
}