package com.mfolivas.atlas.controller;

import com.mfolivas.atlas.domain.InvalidIpAddress;
import com.mfolivas.atlas.domain.IpRequest;
import com.mfolivas.atlas.ipinfo.IpInfoGeoLocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Controller to fetch the geo location from a particular IP by leveraging a third party provider.
 * @author marcelo.
 */
@RestController
public class IpGeoLocationController {


    private final IpInfoGeoLocationService ipInfoGeoLocationService;

    @Inject
    public IpGeoLocationController(IpInfoGeoLocationService ipInfoGeoLocationService) {
        this.ipInfoGeoLocationService = ipInfoGeoLocationService;
    }

    @RequestMapping(value = "/{ip:.*}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public GeoLocationResponse extractGeoLocationByIp(@PathVariable String ip) throws Exception {
        IpRequest ipRequest = IpRequest.valueOf(ip);
        return ipInfoGeoLocationService.extractIpInformation(ipRequest);
    }

    @ExceptionHandler(InvalidIpAddress.class)
    public ResponseEntity<String> invalidIpAddress(InvalidIpAddress invalidIpAddress) {
        return new ResponseEntity<String>(invalidIpAddress.getMessage(), HttpStatus.BAD_REQUEST);
    }

}