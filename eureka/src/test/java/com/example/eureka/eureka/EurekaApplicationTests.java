package com.example.eureka.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
    "eureka.client.enabled=false",
    "eureka.server.enable-self-preservation=false"
})
@EnableAutoConfiguration(exclude = {
    org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration.class
})
@ActiveProfiles("test")
class EurekaApplicationTests {

	@Test
	void contextLoads() {
	}

}
