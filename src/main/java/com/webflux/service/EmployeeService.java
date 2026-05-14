package com.webflux.service;

import com.webflux.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Mono<Employee> save(Employee employee);

    Mono<Employee> getById(String id);

    Flux<Employee> getAll();

    Mono<Employee> update(String id, Employee employee);

    Mono<Void> delete(String id);
}
