package com.mfolivas.atlas.ipinfo;

import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;
import com.mfolivas.atlas.service.IpGeoLocationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Random;

/**
 * Implementation for the ipinfo.io.
 */
@Service
public class IpInfoGeoLocationService  {

    private final IpInfoConfiguration ipInfoConfiguration;
    private static final String GEO_LOCATION_ONLY = "/{ip}/geo";
    private final RestTemplate restTemplate = new RestTemplate();

    //temporary for testing purposes
    private final Random random = new Random();

    @Inject
    public IpInfoGeoLocationService(IpInfoConfiguration ipInfoConfiguration) {
        this.ipInfoConfiguration = ipInfoConfiguration;
    }

    @HystrixCommand(fallbackMethod = "defaultGeoName", groupKey = "GeoLocators", commandKey = "ipinfo.io")
    public GeoLocationResponse extractIpInformation(IpRequest ipRequest) throws Exception {
        //TODO(marcelo) need to figure out the caching aspect
        Thread.sleep(random.nextInt(3) * 1000);
        return restTemplate.getForObject(ipInfoConfiguration.getHost() + GEO_LOCATION_ONLY, GeoLocationResponse.class, ipRequest.getIp());
    }

    public GeoLocationResponse defaultGeoName(IpRequest ipRequest) {
        GeoLocationResponse google = new GeoLocationResponse();
        google.setIp("8.8.8.8");
        google.setCity("Mountain View");
        google.setRegion("California");
        google.setCountry("US");
        google.setLoc("37.3860,-122.0838");
        google.setPostal("94040");
        return google;
    }


}
