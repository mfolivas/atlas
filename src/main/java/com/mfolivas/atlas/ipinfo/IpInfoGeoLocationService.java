package com.mfolivas.atlas.ipinfo;

import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;
import com.mfolivas.atlas.service.IpGeoLocationService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
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
    public GeoLocationResponse extractIpInformation(IpRequest ipRequest) throws Exception {
        //To enable caching we need to enable context: https://github.com/Netflix/Hystrix/wiki/How-To-Use#request-context-setup
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            IpInfoGeoLocationCommand ipInfoGeoLocationCommand = new IpInfoGeoLocationCommand(ipInfoConfiguration, ipRequest);
            return ipInfoGeoLocationCommand.execute();
        } finally {
            context.shutdown();
        }


    }


}
