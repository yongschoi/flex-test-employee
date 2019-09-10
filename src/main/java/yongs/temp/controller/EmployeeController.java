package yongs.temp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yongs.temp.model.Employee;
import yongs.temp.service.EmployeeService;

@RestController
public class EmployeeController {
	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
    private EmployeeService service;
	
    @PostMapping("/employee/create") /* Postman 프로그램으로 실행 */
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e) {
    	logger.debug("flex-employee|EmployeeController|create()");
        service.create(e);
    }
    
    @GetMapping("/employee/id/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
    	logger.debug("flex-employee|EmployeeController|findById({})", id);
    	Mono<Employee> e = service.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Employee>>(e, status);
    }
 
    @GetMapping("/employee/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
    	logger.debug("flex-employee|EmployeeController|findByName({})", name);
        return service.findByName(name);
    }
 
    @GetMapping("/employees")
    public Flux<Employee> findAll() {
    	logger.debug("flex-employee|EmployeeController|findAll()");    	
        Flux<Employee> emps = service.findAll();
        return emps;
    }
 
    @PutMapping("/employee/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e) {
    	logger.debug("flex-employee|EmployeeController|update()");    	    	
        return service.update(e);
    }
 
    @DeleteMapping("/employee/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") Integer id) {
    	logger.debug("flex-employee|EmployeeController|delete({})", id);      	
        return service.delete(id);
    }
}
