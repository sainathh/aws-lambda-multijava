package com.mortgage.loans.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mortgage.loans.api.models.Task;

public interface ITaskDao extends CrudRepository<Task, Long>{

	@Query("select c from Task c where c.assign_to=?1 and c.delete=0")
	List<Task> getTaskByEmpId(Long id);
}
