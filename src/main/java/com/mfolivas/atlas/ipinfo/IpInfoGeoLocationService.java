package com.mfolivas.atlas.ipinfo;

import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import javax.inject.Inject;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * Implementation for the ipinfo.io.
 */
@Service
public class IpInfoGeoLocationService {
    private static final Logger log = LoggerFactory.getLogger(IpInfoGeoLocationService.class);

    private final IpInfoConfiguration ipInfoConfiguration;
    private static final String GEO_LOCATION_ONLY = "/{ip}/geo";
    private final RestTemplate restTemplate = new RestTemplate();

    //temporary for testing purposes
    private final Random random = new Random();

    private final Counter counter;
    private final Timer timer;

    @Inject
    public IpInfoGeoLocationService(IpInfoConfiguration ipInfoConfiguration, MeterRegistry meterRegistry) {
        this.ipInfoConfiguration = ipInfoConfiguration;
        this.counter = meterRegistry.counter("ipinfo.fallback");
        this.timer = meterRegistry.timer("ipinfo.latency");
    }

    @HystrixCommand(fallbackMethod = "defaultGeoName", groupKey = "geoLocators", commandKey = "ipinfo")
    public GeoLocationResponse extractIpInformation(IpRequest ipRequest) {
        return restTemplate.getForObject(ipInfoConfiguration.getHost() + GEO_LOCATION_ONLY, GeoLocationResponse.class, ipRequest.getIp());
    }

    public GeoLocationResponse defaultGeoName(IpRequest ipRequest) {
        log.warn("Timeout from the provider. Using default geo name");
        counter.increment();
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