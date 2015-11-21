package com.mfolivas.atlas.service;

import com.mfolivas.atlas.domain.GeoInformation;
import com.mfolivas.atlas.domain.IpRequest;
import org.springframework.stereotype.Service;

/**
 * Implementation for the ipinfo.io.
 */

@Service
public class IpInfoAddressService implements IpAddressService {

    @Override
    public GeoInformation extractIpInformation(IpRequest ipRequest) {
        return null;
    }
}
