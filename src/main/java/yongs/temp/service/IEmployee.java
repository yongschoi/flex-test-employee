package yongs.temp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yongs.temp.model.Employee;

public interface IEmployee {
    void create(Employee e); 
    Mono<Employee> findById(Integer id);
    Flux<Employee> findByName(String name);
    Flux<Employee> findAll();
    Mono<Employee> update(Employee e);
    Mono<Void> delete(Integer id);
}
