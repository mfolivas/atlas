package com.mfolivas.atlas.domain;

/**
 * The default will be the location of Google's headquarters.
 */
public final class DefaultGeoInformation {

    private static final Double LATITUDE = 37.385999999999996;
    private static final Double LONGITUDE = -122.0838;
    private static final String CITY =  "Mountain View";
    private static final String REGION =  "California";
    private static final String COUNTRY =  "US";
    private static final String POSTAL_CODE =  "94040";


    private static final Coordinates GOOGLE_COORDINATES = Coordinates.valueOf(LATITUDE, LONGITUDE);
    private static final Location GOOGLE_LOCATION = Location.valueOf(CITY, REGION, COUNTRY, POSTAL_CODE);

    public static GeoInformation valueOf(IpRequest ipRequest) {
        return GeoInformation.valueOf(ipRequest, GOOGLE_COORDINATES, GOOGLE_LOCATION);
    }

}
