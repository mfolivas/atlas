package com.mfolivas.atlas.service;

import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;

/**
 * Retrieve IP information back to the client.
 */
public interface IpGeoLocationService {
    GeoLocationResponse extractIpInformation(IpRequest ipRequest) throws Exception;
}