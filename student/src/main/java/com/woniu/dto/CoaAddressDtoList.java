package com.woniu.dto;

public class CoaAddressDtoList {
    private double longitude;
    private double latitude;
    private String coaId;

    public String getCoaId() {
        return coaId;
    }

    public void setCoaId(String coaId) {
        this.coaId = coaId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
