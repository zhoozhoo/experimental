package ca.zhoozhoo.experimental.reactive.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class EmployeeControllerTest {

	@Container
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0")
			.withDatabaseName("springbootdb")
			.withUsername("postgres")
			.withPassword("postgres");

	@Autowired
	private WebTestClient webClient;

	@Test
	public void create() {
		webClient.get().uri("/employees")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	public void get() {
		webClient.get().uri("/employees")
				.exchange()
				.expectStatus().isOk();
	}


	@DynamicPropertySource
	private static void setDatasourceProperties(DynamicPropertyRegistry registry) {
		// JDBC DataSource Example
		registry.add("spring.liquibase.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.liquibase.user", postgreSQLContainer::getUsername);
		registry.add("spring.liquibase.password", postgreSQLContainer::getPassword);

		// R2DBC DataSource Example
		registry.add("spring.r2dbc.url", () -> String.format("r2dbc:pool:postgresql://%s:%d/%s",
				postgreSQLContainer.getHost(),
				postgreSQLContainer.getFirstMappedPort(),
				postgreSQLContainer.getDatabaseName()));
		registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername);
		registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword);
	}
}
