package com.webflux.controller;

import com.webflux.entity.Employee;
import com.webflux.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public Mono<Employee> save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/get/{id}")
    public Mono<Employee> getById(@PathVariable String id){
        return employeeService.getById(id);
    }

    @GetMapping("/getAll")
    public Flux<Employee> getAll(){
        return employeeService.getAll();
    }

    @PutMapping("/update/{id}")
    public Mono<Employee> update(@PathVariable String id,
                                 @RequestBody Employee employee){
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return employeeService.delete(id);
    }
}
