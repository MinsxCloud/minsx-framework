package com.minsx.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {

    @Value("${security.oauth2.client.client-id}")
	public String clientId;

    @Value("${security.oauth2.client.client-secret}")
	public String clientSecret;


}
