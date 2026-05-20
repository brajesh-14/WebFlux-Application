package com.webflux.controller;

import com.webflux.entity.Employee;
import com.webflux.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public Mono<ResponseEntity<Employee>> save(@RequestBody Employee employee){
        return employeeService.save(employee)
                .map(emp -> ResponseEntity.status(201).body(emp));
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Employee>> getById(@PathVariable String id){
        return employeeService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build()
                );
    }

    @GetMapping("/getAll")
    public Mono<ResponseEntity<Flux<Employee>>> getAll(){
        Flux<Employee> all = employeeService.getAll();
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(all));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Employee>> update(@PathVariable String id,
                                 @RequestBody Employee employee){
        return employeeService.update(id, employee)
                .map(emp -> ResponseEntity.status(201).body(emp))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return employeeService.delete(id)
                .then(Mono.just(ResponseEntity.noContent()
                        .build()
                ));
    }
}
