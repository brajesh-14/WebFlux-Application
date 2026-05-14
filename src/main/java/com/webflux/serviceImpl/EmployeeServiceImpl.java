package com.webflux.serviceImpl;

import com.webflux.entity.Employee;
import com.webflux.repository.EmployeeRepository;
import com.webflux.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Employee> getById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> update(String id, Employee employee) {
        return employeeRepository.findById(id)
                .flatMap(existing -> {

                    existing.setName(employee.getName());
                    existing.setDept(employee.getDept());
                    existing.setSalary(employee.getSalary());

                    return employeeRepository.save(existing);
                });

    }

    @Override
    public Mono<Void> delete(String id) {
        return employeeRepository.deleteById(id);
    }
}
