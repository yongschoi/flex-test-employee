package yongs.temp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yongs.temp.dao.EmployeeRepository;
import yongs.temp.model.Employee;

@Service
public class EmployeeService implements IEmployee {
	private Logger logger = LoggerFactory.getLogger(EmployeeService.class);	

	@Autowired
    EmployeeRepository repo;
	
	@Override
	public void create(Employee e) {
		logger.debug("flex-employee|EmployeeService|create()");
		repo.save(e).subscribe();
	}

	@Override
	public Mono<Employee> findById(Integer id) {
		logger.debug("flex-employee|EmployeeService|findById({})", id);
		return repo.findById(id);
	}

	@Override
	public Flux<Employee> findByName(String name) {
		logger.debug("flex-employee|EmployeeService|findByName({})", name);
		return repo.findByName(name);
	}

	@Override
	public Flux<Employee> findAll() {
		logger.debug("flex-employee|EmployeeService|findAll()"); 	
		try {
			Thread.sleep(30000); // 5초 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return repo.findAll();
	}

	@Override
	public Mono<Employee> update(Employee e) {
		logger.debug("flex-employee|EmployeeService|update()");
		return repo.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		logger.debug("flex-employee|EmployeeService|delete({})", id);
		return repo.deleteById(id);
	}
}
