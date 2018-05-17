package com.mfolivas.atlas.controller;

import com.mfolivas.atlas.domain.InvalidIpAddress;
import com.mfolivas.atlas.domain.IpRequest;
import com.mfolivas.atlas.ipinfo.IpInfoGeoLocationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Controller to fetch the geo location from a particular IP by leveraging a third party provider.
 */
@RestController
public class IpGeoLocationController {


    private final IpInfoGeoLocationService ipInfoGeoLocationService;
    private final Counter counter;

    @Inject
    public IpGeoLocationController(IpInfoGeoLocationService ipInfoGeoLocationService, MeterRegistry meterRegistry) {
        this.ipInfoGeoLocationService = ipInfoGeoLocationService;
        this.counter = meterRegistry.counter("received.ip");
    }

    @GetMapping("/{ip:.*}")
    public GeoLocationResponse extractGeoLocationByIp(@PathVariable String ip) throws Exception {
        this.counter.increment();
        IpRequest ipRequest = IpRequest.valueOf(ip);
        return ipInfoGeoLocationService.extractIpInformation(ipRequest);
    }

    @ExceptionHandler(InvalidIpAddress.class)
    public ResponseEntity<String> invalidIpAddress(InvalidIpAddress invalidIpAddress) {
        return new ResponseEntity<String>(invalidIpAddress.getMessage(), HttpStatus.BAD_REQUEST);
    }

}