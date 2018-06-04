package com.mfolivas.atlas.ipinfo;

import com.mfolivas.atlas.controller.GeoLocationResponse;
import com.mfolivas.atlas.domain.IpRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Testing the ipinfo integration.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"ipinfo.host=http://localhost:6064"},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureWireMock(port = 6064)
public class IpInfoGeoLocationServiceTest {

    private static final String IP = "75.106.116.234";

    @Inject
    private IpInfoGeoLocationService ipInfoGeoLocationService;

    @Test
    public void shouldReturnTheProperIpAndLocationWhenFetchingRequest() throws Exception {
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

        IpRequest ipRequest = IpRequest.valueOf(IP);
        GeoLocationResponse geoLocationResponse = ipInfoGeoLocationService.extractIpInformation(ipRequest);
        assertThat(geoLocationResponse, is(notNullValue()));
        assertThat(geoLocationResponse.getIp(), is(IP));
        assertThat(geoLocationResponse.getLoc(), is("35.2031,-85.9211"));

    }

    @Test
    public void shouldReturnFallbackRequestWhenTimeIsLongerThanOneSecond() throws Exception {
        String localHost = "127.0.0.1";
        stubFor(get(urlMatching(".*/geo"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withFixedDelay(50000)
                        .withStatus(200)
                                .withBody("{}")));

        IpRequest ipRequest = IpRequest.valueOf(localHost);
        GeoLocationResponse geoLocationResponse = ipInfoGeoLocationService.extractIpInformation(ipRequest);
        assertThat(geoLocationResponse, is(notNullValue()));
        assertThat(geoLocationResponse.getIp(), is(not(localHost)));
        assertThat(geoLocationResponse.getLoc(), is(not("35.2031,-85.9211")));
        assertThat(geoLocationResponse.getIp(), is("8.8.8.8"));
    }
}

