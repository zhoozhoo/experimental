package ca.zhoozhoo.experimental.reactive.service;

import java.util.UUID;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

import ca.zhoozhoo.experimental.reactive.model.Employee;
import ca.zhoozhoo.experimental.reactive.repository.EmployeeRepository;

@DataR2dbcTest
@ActiveProfiles("test")
class DemoApplicationTests {

	@ClassRule
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0")
			.withDatabaseName("springbootdb")
			.withUsername("postgres")
			.withPassword("postgres");

	@Autowired
	private R2dbcEntityTemplate template;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testInsertAndQuery() {
		var data = new Employee(UUID.randomUUID(), "name");
		this.template.insert(data)
				.thenMany(employeeRepository.findAll())
				.log();

	}

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of(
					"spring.liquibase.url=" + postgreSQLContainer.getJdbcUrl(),
					"spring.liquibase.user=" + postgreSQLContainer.getUsername(),
					"spring.liquibase.password=" + postgreSQLContainer.getPassword())
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}
}
