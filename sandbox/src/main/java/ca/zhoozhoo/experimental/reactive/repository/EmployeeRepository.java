package ca.zhoozhoo.experimental.reactive.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.zhoozhoo.experimental.reactive.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, UUID> {
}
