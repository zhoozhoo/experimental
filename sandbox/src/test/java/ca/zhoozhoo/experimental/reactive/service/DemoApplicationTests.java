package ca.zhoozhoo.experimental.reactive.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ca.zhoozhoo.experimental.reactive.model.Employee;
import ca.zhoozhoo.experimental.reactive.repository.EmployeeRepository;

@DataR2dbcTest
@ActiveProfiles("test")
@Testcontainers
class DemoApplicationTests {

	@Autowired
	private R2dbcEntityTemplate template;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testDatabaseClientExisted() {
		assertNotNull(template);
	}

	@Test
	public void testPostRepositoryExisted() {
		assertNotNull(employeeRepository);
		System.out.println("Done");
	}

	@Test
	public void testInsertAndQuery() {
		var data = new Employee(UUID.randomUUID(), "name");
		this.template.insert(data)
				.thenMany(employeeRepository.findAll())
				.log();

	}
}
