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
    private String commandGroupKey;
    private String commandKey;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCommandGroupKey() {
        return commandGroupKey;
    }

    public void setCommandGroupKey(String commandGroupKey) {
        this.commandGroupKey = commandGroupKey;
    }

    public String getCommandKey() {
        return commandKey;
    }

    public void setCommandKey(String commandKey) {
        this.commandKey = commandKey;
    }
}
