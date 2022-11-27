package ca.zhoozhoo.experimental.reactive.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.zhoozhoo.experimental.reactive.model.Employee;
import ca.zhoozhoo.experimental.reactive.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping
    public Mono<Employee> create(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    private Mono<Employee> get(@PathVariable UUID id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    private Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
