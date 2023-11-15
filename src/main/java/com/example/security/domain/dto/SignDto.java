package com.example.security.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SignDto {

    private Integer id;

    @NotEmpty(message = "Sign needs a title")
    private String title;

    @NotEmpty(message = "Sign needs a description")
    private String description;

    @NotNull(message = "Sign needs Latitude coordinates")
    private double lat;

    @NotNull(message = "Sign needs Longitude coordinates")
    private double lon;

    private Integer imageId;

    public SignDto(String title, String description, double lat, double lon) {
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
    }

    public SignDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
