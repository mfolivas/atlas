package com.mfolivas.atlas.ipinfo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration parameters for the application.
 */
@Component
@ConfigurationProperties("ipinfo")
public class IpInfoConfiguration {

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
