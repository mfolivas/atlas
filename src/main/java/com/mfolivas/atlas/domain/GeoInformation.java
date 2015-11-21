package com.mfolivas.atlas.domain;

import java.util.Objects;

/**
 * Payload for the creation of the information of the IP, including geo location.
 */
public final class GeoInformation {
    private final IpRequest ipRequest;
    private final Coordinates coordinates;
    private final Location location;

    private GeoInformation(IpRequest ipRequest, Coordinates coordinates, Location location) {
        this.ipRequest = ipRequest;
        this.coordinates = coordinates;
        this.location = location;
    }

    public static GeoInformation valueOf(IpRequest ipRequest, Coordinates coordinates, Location location) {
        return new GeoInformation(ipRequest, coordinates, location);
    }

    public static GeoInformation defaultValue(IpRequest ipRequest) {
        return DefaultGeoInformation.valueOf(ipRequest);
    }

    public IpRequest getIpRequest() {
        return ipRequest;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoInformation that = (GeoInformation) o;
        return Objects.equals(getIpRequest(), that.getIpRequest()) &&
                Objects.equals(getCoordinates(), that.getCoordinates()) &&
                Objects.equals(getLocation(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIpRequest(), getCoordinates(), getLocation());
    }

    @Override
    public String toString() {
        return "GeoInformation{" +
                "ipRequest=" + ipRequest +
                ", coordinates=" + coordinates +
                ", location=" + location +
                '}';
    }
}
