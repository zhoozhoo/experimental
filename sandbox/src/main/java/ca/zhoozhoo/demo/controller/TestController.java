package ca.zhoozhoo.demo.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.zhoozhoo.demo.model.Employee;
import ca.zhoozhoo.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class TestController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    private Mono<Employee> getEmployeeById(@PathVariable UUID id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    private Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
