package com.mortgage.loans.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mortgage.loans.api.models.User;

public interface IUserDao extends CrudRepository<User, Long>{

	@Query("select c from User c where c.email=?1")
	List<User> findByEmail(String email);
	
	@Query("select c from User c where c.delete=0")
	List<User> findAllNonDeleted();
	
	@Query("select c from User c where c.delete=0 and c.first_name like %:first_name% and c.last_name like %:last_name% and c.email like %:email%")
	List<User> searchWithoutRole(@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("email") String email);
}
