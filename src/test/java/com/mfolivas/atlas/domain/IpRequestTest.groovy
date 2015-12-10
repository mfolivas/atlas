package com.mfolivas.atlas.domain

import spock.lang.Specification

/**
 * Testing the IpRequest.
 */
class IpRequestTest extends Specification {
    def "Retrieve valid IPv4 address"() {
        given: "a valid IP address"
        def ipAddress = "8.8.8.8"

        when: "requesting an instance of the ip request"
        def ipRequest = IpRequest.valueOf(ipAddress)

        then: "the ip should match the request"
        ipRequest.ip == ipAddress

    }

    def "Retrieve valid IPv6 address"() {
        given: "a valid IP address"
        def ipAddress = "2601:9:7680:363:75df:f491:6f85:352f"

        when: "requesting an instance of the ip request"
        def ipRequest = IpRequest.valueOf(ipAddress)

        then: "the ip should match the request"
        ipRequest.ip == ipAddress

    }

    def "Exception when passing a null as the ip"() {
        when: "an ip is null"
        IpRequest.valueOf(null)

        then: "an exception should be thrown"
        InvalidIpAddress exception = thrown()
        exception.getMessage() == "The following ip is not valid [null]"

    }

    def "Exception when passing a blank ip address"() {
        when: "an ip is null"
        IpRequest.valueOf("")

        then: "an exception should be thrown"
        InvalidIpAddress exception = thrown()
        exception.getMessage() == "The following ip is not valid []"

    }

    def "Exception when passing an invalid ip address"() {
        when: "an ip is invalid"
        IpRequest.valueOf("8.8.8.")

        then: "an exception should be thrown"
        InvalidIpAddress exception = thrown()
        exception.getMessage() == "The following ip is not valid [8.8.8.]"

    }
}
