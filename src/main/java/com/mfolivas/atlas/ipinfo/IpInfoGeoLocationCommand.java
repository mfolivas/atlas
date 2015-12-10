package com.mfolivas.atlas.ipinfo;

import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;
import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

/**
 * Command to call ipinfo.io and retrieve the geo location leveraging Hystrix.
 * Just call the ipinfo.io/{ip}/geo just to get the geo location.  We enable cache to increase performance.
 * Example: http://ipinfo.io/8.8.8.8/geo would return in the following:
 * {
 * "ip": "8.8.8.8",
 * "city": "Mountain View",
 * "region": "California",
 * "country": "US",
 * "loc": "37.3860,-122.0838",
 * "postal": "94040"
 * }
 *
 * @see <a href="https://github.com/Netflix/Hystrix">Netflix's Hystrix</a>.
 *
 * @author Marcelo Olivas.
 */
public class IpInfoGeoLocationCommand extends HystrixCommand<GeoLocationResponse> {

    private static final String GEO_LOCATION_ONLY = "/{ip}/geo";

    private final IpInfoConfiguration ipInfoConfiguration;
    private final IpRequest ipRequest;


    public IpInfoGeoLocationCommand(IpInfoConfiguration ipInfoConfiguration, IpRequest ipRequest) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(ipInfoConfiguration.getCommandGroupKey()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(ipInfoConfiguration.getCommandKey()))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(IpInfoGeoLocationCommand.class.getName())));

        this.ipInfoConfiguration = ipInfoConfiguration;
        this.ipRequest = ipRequest;
    }

    @Override
    protected GeoLocationResponse run() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ipInfoConfiguration.getHost() + GEO_LOCATION_ONLY, GeoLocationResponse.class, ipRequest.getIp());
    }

    @Override
    protected GeoLocationResponse getFallback() {
        return googleGeoLocation();
    }

    @Override
    protected String getCacheKey() {
        return ipRequest.getIp();
    }

    private static GeoLocationResponse googleGeoLocation() {
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