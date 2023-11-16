package com.bayutb123.dansapp.model;

import com.google.gson.annotations.SerializedName;

public class Jobs {
    @SerializedName("company_logo")
    private String companyLogo;
    @SerializedName("how_to_apply")
    private String howToApply;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("description")
    private String description;
    @SerializedName("company")
    private String company;
    @SerializedName("company_url")
    private String companyUrl;
    @SerializedName("location")
    private String location;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;

    public Jobs(String companyLogo, String howToApply, String createdAt, String description, String company, String companyUrl, String location, String id, String type, String title, String url) {
        this.companyLogo = companyLogo;
        this.howToApply = howToApply;
        this.createdAt = createdAt;
        this.description = description;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.id = id;
        this.type = type;
        this.title = title;
        this.url = url;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
