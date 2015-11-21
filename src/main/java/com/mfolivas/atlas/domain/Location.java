package com.mfolivas.atlas.domain;

import java.util.Objects;
import java.util.Optional;

/**
 * Location of the IP address.
 */
public final class Location {
    private final Optional<String> city;
    private final Optional<String> region;
    private final Optional<String> country;
    private final Optional<String> postalCode;

    private Location(String city, String region, String country, String postalCode) {
        this.city = Optional.of(city);
        this.region = Optional.of(region);
        this.country = Optional.of(country);
        this.postalCode = Optional.of(postalCode);
    }

    public static Location valueOf(String city, String region, String country, String postalCode) {
        return new Location(city, region, country, postalCode);
    }

    public Optional<String> getCity() {
        return city;
    }

    public Optional<String> getRegion() {
        return region;
    }

    public Optional<String> getCountry() {
        return country;
    }

    public Optional<String> getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(getCity(), location.getCity()) &&
                Objects.equals(getRegion(), location.getRegion()) &&
                Objects.equals(getCountry(), location.getCountry()) &&
                Objects.equals(getPostalCode(), location.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getRegion(), getCountry(), getPostalCode());
    }

    @Override
    public String toString() {
        return "Location{" +
                "city=" + city +
                ", region=" + region +
                ", country=" + country +
                ", postalCode=" + postalCode +
                '}';
    }
}
