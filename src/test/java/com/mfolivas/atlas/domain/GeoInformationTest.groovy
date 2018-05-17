package com.mfolivas.atlas.domain

import spock.lang.Specification

/**
 * Creating test for the GeoInformation class.
 */
class GeoInformationTest extends Specification {
    def "retrieve a geo information"() {
        given: "an ip address, coordinates, and location"

        def ipRequest = IpRequest.valueOf("99.117.133.228")
        def coordinates = Coordinates.valueOf(25.6586, -80.3568)
        def location = Location.valueOf("Miami", "Fl", "US", "33133")

        when: "instantiating the class"
        def geoLocation = GeoInformation.valueOf(ipRequest, coordinates, location)

        then: "the response should match the request"
        geoLocation.ipRequest == ipRequest
        geoLocation.coordinates == coordinates
        geoLocation.location == location
    }

    def "request the default geo location"() {
        given: "an ip address"
        def ipRequest = IpRequest.valueOf("99.117.133.228")

        when: "calling the default geo location"
        def geoLocation = GeoInformation.defaultValue(ipRequest)

        then: "the ip address should be the same, but the response should match the Google's address"
        geoLocation.ipRequest == ipRequest
        geoLocation.coordinates == Coordinates.valueOf(37.385999999999996, -122.0838)
        geoLocation.location == Location.valueOf("Mountain View", "California", "US", "94040")

    }
}
