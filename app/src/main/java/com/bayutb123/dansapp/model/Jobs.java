package com.bayutb123.dansapp.model;

import com.google.gson.annotations.SerializedName;

public class Jobs {
    @SerializedName("company_logo")
    private final String companyLogo;
    @SerializedName("how_to_apply")
    private final String howToApply;
    @SerializedName("created_at")
    private final String createdAt;
    @SerializedName("description")
    private final String description;
    @SerializedName("company")
    private final String company;
    @SerializedName("company_url")
    private final String companyUrl;
    @SerializedName("location")
    private final String location;
    @SerializedName("id")
    private final String id;
    @SerializedName("type")
    private final String type;
    @SerializedName("title")
    private final String title;
    @SerializedName("url")
    private final String url;

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
