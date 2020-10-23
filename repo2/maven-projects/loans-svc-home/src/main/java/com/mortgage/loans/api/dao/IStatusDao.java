package com.mortgage.loans.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.mortgage.loans.api.models.Status;

public interface IStatusDao extends CrudRepository<Status, Long>{

}
