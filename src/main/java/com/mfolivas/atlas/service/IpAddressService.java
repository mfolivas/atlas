package com.mfolivas.atlas.service;

import com.mfolivas.atlas.domain.GeoInformation;
import com.mfolivas.atlas.domain.IpRequest;

/**
 * Retrieve IP information back to the client.
 */
public interface IpAddressService {
    GeoInformation extractIpInformation(IpRequest ipRequest);
}
