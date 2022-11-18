package ca.zhoozhoo.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import ca.zhoozhoo.demo.model.Employee;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, UUID> {
}
