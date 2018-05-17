package com.mfolivas.atlas.controller;

import java.util.Objects;

/**
 * @author Marcelo Olivas.
 */
public class GeoLocationResponse {
    private String ip;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String postal;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoLocationResponse that = (GeoLocationResponse) o;
        return Objects.equals(ip, that.ip) &&
                Objects.equals(city, that.city) &&
                Objects.equals(region, that.region) &&
                Objects.equals(country, that.country) &&
                Objects.equals(loc, that.loc) &&
                Objects.equals(postal, that.postal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ip, city, region, country, loc, postal);
    }

    @Override
    public String toString() {
        return "GeoLocationResponse{" +
                "ip='" + ip + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", loc='" + loc + '\'' +
                ", postal='" + postal + '\'' +
                '}';
    }
}