package com.mfolivas.atlas.ipinfo;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


/**
 * Testing the ipinfo integration.
 */
public class IpInfoGeoLocationServiceTest {

    public static final String IP = "75.106.116.234";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void testExtractIpInformation() throws Exception {
        stubFor(get(urlMatching(".*/geo"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200).withBody("{\n" +
                        "  \"ip\": \"" + IP + "\",\n" +
                        "  \"hostname\": \"No Hostname\",\n" +
                        "  \"city\": \"Sewanee\",\n" +
                        "  \"region\": \"Tennessee\",\n" +
                        "  \"country\": \"US\",\n" +
                        "  \"loc\": \"35.2031,-85.9211\",\n" +
                        "  \"org\": \"AS7155 ViaSat,Inc.\",\n" +
                        "  \"postal\": \"37375\"\n" +
                        "}")));
        IpInfoConfiguration ipInfoConfiguration = new IpInfoConfiguration();
        ipInfoConfiguration.setCommandGroupKey("ipinfo");
        ipInfoConfiguration.setCommandKey("ipinfo");
        ipInfoConfiguration.setHost("http://localhost:8080");
        IpRequest ipRequest = IpRequest.valueOf(IP);
        IpInfoGeoLocationService ipInfoGeoLocationService = new IpInfoGeoLocationService(ipInfoConfiguration);
        GeoLocationResponse geoLocationResponse = ipInfoGeoLocationService.extractIpInformation(ipRequest);
        assertNotNull(geoLocationResponse);
        assertEquals(geoLocationResponse.getLoc(), "35.2031,-85.9211");

    }
}