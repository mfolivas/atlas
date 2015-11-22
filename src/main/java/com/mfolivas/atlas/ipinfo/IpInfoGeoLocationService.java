package com.mfolivas.atlas.ipinfo;

import com.mfolivas.atlas.domain.GeoInformation;
import com.mfolivas.atlas.domain.IpRequest;
import com.mfolivas.atlas.service.IpGeoLocationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Implementation for the ipinfo.io.
 */
@Service
public class IpInfoGeoLocationService implements IpGeoLocationService {

    private final IpInfoConfiguration ipInfoConfiguration;


    @Inject
    public IpInfoGeoLocationService(IpInfoConfiguration ipInfoConfiguration) {
        this.ipInfoConfiguration = ipInfoConfiguration;
    }

    @Override
    public GeoInformation extractIpInformation(IpRequest ipRequest) {
        return null;
    }
}
