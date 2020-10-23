package com.mortgage.loans.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mortgage.loans.api.models.Application;

public interface IApplicationDao extends CrudRepository<Application, Long>{
	
	@Query("select c from Application c where c.status=?1 and c.delete=0")
	List<Application> findByStatus(int status);
	
	@Query("select c from Application c where c.status=?1 and c.assigned_to=?2 and c.delete=0")
	List<Application> getApplicationsByStatusIdAndEmpId(int status, Long empId);
	
	@Query("select c from Application c where c.customer_id=?1 and c.delete=0")
	List<Application> findAllByCustomerId(Long id);
	
	@Query("select c from Application c where c.assigned_to=?1 and c.delete=0")
	List<Application> findAllByAssignedId(Long id);
	
	@Query("select c from Application c where c.delete=0")
	List<Application> findAllNonDeleted();
	
	@Query("select c from Application c where c.delete=0 and c.first_name like %:first_name% and c.last_name like %:last_name% and c.email like %:email% and c.phone like %:phone%")
	List<Application> search(@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("email") String email, @Param("phone") String phone);
	
	@Query("select c from Application c where c.delete=0 and c.assigned_to = :assigned_to and c.first_name like %:first_name% and c.last_name like %:last_name% and c.email like %:email% and c.phone like %:phone%")
	List<Application> searchByEmpId(@Param("assigned_to") Long assigned_to, @Param("first_name") String first_name, @Param("last_name") String last_name, @Param("email") String email, @Param("phone") String phone);
}
