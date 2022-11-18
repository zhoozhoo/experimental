package ca.zhoozhoo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import dasniko.testcontainers.keycloak.KeycloakContainer;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class DemoApplicationTests {

	@Container
	private KeycloakContainer keycloak = new KeycloakContainer("quay.io/keycloak/keycloak:20.0.0");

	@Test
	void contextLoads() {
	}
}
