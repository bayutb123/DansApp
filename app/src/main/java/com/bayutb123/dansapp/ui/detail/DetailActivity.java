package com.bayutb123.dansapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.bayutb123.dansapp.R;
import com.bayutb123.dansapp.databinding.ActivityDetailBinding;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    DetailViewModel detailViewModel;
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupActionBar();

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        String id = getIntent().getStringExtra("id");
        detailViewModel.setJobsDetail(id);

        detailViewModel.jobsDetail.observe(this, jobs -> {
            Log.d("DetailActivity", "onCreate: " + jobs);
            binding.tvJobTitle.setText(jobs.getTitle());
            binding.tvJobCompany.setText(jobs.getCompany());
            binding.tvJobLocation.setText(jobs.getLocation());
            binding.tvJobDescription.setText(jobs.getDescription());
            binding.tvJobType.setText(jobs.getType());

            Glide.with(this)
                    .load(jobs.getCompanyLogo())
                    .placeholder(R.drawable.logo_placeholder)
                    .into(binding.ivJobImage);
        });
    }

    private void setupActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Job");
    }
}