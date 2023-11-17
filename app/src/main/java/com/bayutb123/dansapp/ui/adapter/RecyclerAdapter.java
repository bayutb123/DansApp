package com.bayutb123.dansapp.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bayutb123.dansapp.R;
import com.bayutb123.dansapp.model.Jobs;
import com.bayutb123.dansapp.ui.detail.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final String TAG = "RecyclerAdapter";
    private final List<Jobs> jobsList;
    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Jobs jobs);
    }

    public RecyclerAdapter(
            List<Jobs> jobsList,
            OnItemClickListener listener
    ) {
        this.jobsList = jobsList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.bayutb123.dansapp.R.layout.job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jobs jobs = jobsList.get(position);
        if (jobs != null) {
            holder.getJobTitle().setText(jobs.getTitle());
            holder.getJobCompany().setText(jobs.getCompany());
            holder.getJobLocation().setText(jobs.getLocation());
            Glide.with(holder.itemView.getContext())
                    .load(jobs.getCompanyLogo())
                    .placeholder(R.drawable.logo_placeholder)
                    .into(holder.getJobImage());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(jobs);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView jobTitle;
        public TextView jobCompany;
        public TextView jobLocation;
        public ImageView jobImage;
        public ViewHolder(View view) {
            super(view);
            jobTitle = view.findViewById(com.bayutb123.dansapp.R.id.jobTitle);
            jobCompany = view.findViewById(com.bayutb123.dansapp.R.id.jobCompany);
            jobLocation = view.findViewById(com.bayutb123.dansapp.R.id.jobLocation);
            jobImage = view.findViewById(com.bayutb123.dansapp.R.id.jobImage);
        }

        public TextView getJobTitle() {
            return jobTitle;
        }

        public TextView getJobCompany() {
            return jobCompany;
        }

        public TextView getJobLocation() {
            return jobLocation;
        }

        public ImageView getJobImage() {
            return jobImage;
        }
    }


}
