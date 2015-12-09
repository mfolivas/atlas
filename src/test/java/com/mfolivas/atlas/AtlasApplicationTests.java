package com.mfolivas.atlas;

import com.mfolivas.atlas.controller.IpInfoResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AtlasApplication.class)
@WebAppConfiguration
public class AtlasApplicationTests {

	@Test
	public void contextLoads() {
		String ip = "8.8.8.8";
		RestTemplate restTemplate = new RestTemplate();
		IpInfoResponse response = restTemplate.getForObject("http://ipinfo.io/{ip}/geo", IpInfoResponse.class, ip);
		Assert.assertNotNull(response);
	}

}
