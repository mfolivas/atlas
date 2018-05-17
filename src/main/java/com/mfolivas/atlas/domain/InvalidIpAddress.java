package com.mfolivas.atlas.domain;

/**
 * The IP address provided is either null, empty, or not a valid ip.
 */
public class InvalidIpAddress extends IllegalArgumentException {

    public InvalidIpAddress(String message) {
        super(message);
    }
}