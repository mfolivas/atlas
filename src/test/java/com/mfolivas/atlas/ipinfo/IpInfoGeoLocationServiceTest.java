package com.mfolivas.atlas.ipinfo;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marcelo Olivas
 *
 */
public class IpInfoGeoLocationServiceTest {
    public static final String IP_ADRESS = "99.117.133.228";

    @Rule
    public WireMockRule ipInfoMock = new WireMockRule();

    @Test
    public void shouldReturnAValidIpAddress() throws Exception {
        ipInfoMock.stubFor(get(urlPathEqualTo(IP_ADRESS + "/geo"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200).withBody("{\n" +
                        "\"ip\": \"" + IP_ADRESS + "\",\n" +
                        "\"city\": \"Miami\",\n" +
                        "\"region\": \"Florida\",\n" +
                        "\"country\": \"US\",\n" +
                        "\"loc\": \"25.6586,-80.3568\",\n" +
                        "\"postal\": \"33176\"\n" +
                        "}")));
        IpInfoConfiguration ipInfoConfiguration = new IpInfoConfiguration();
        ipInfoConfiguration.setHost("http://localhost:8080");
        ipInfoConfiguration.setCommandGroupKey("geolocation");
        ipInfoConfiguration.setCommandKey("ipinfo");
        RestTemplate restTemplate = new RestTemplate();
        GeoLocationResponse geoLoc = restTemplate.getForObject("http://localhost:8080/{ip}/geo", GeoLocationResponse.class, IP_ADRESS);
        IpInfoGeoLocationService ipInfoGeoLocationService = new IpInfoGeoLocationService(ipInfoConfiguration);
        GeoLocationResponse geoLocationResponse = ipInfoGeoLocationService.extractIpInformation(IpRequest.valueOf(IP_ADRESS));
        assertNotNull(geoLocationResponse);

    }

    @Test
    public void exampleTest() {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>Some content</response>")));

        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.getForEntity("");

        assertTrue(result.wasSuccessFul());

        verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
                .withRequestBody(matching(".*<message>1234</message>.*"))
                .withHeader("Content-Type", notMatching("application/json")));
    }
}