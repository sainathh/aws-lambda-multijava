package com.mortgage.loans.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mortgage.loans.api.models.Comment;

public interface ICommentDao extends CrudRepository<Comment, Long>{
	@Query("select c from Comment c where c.application_id=?1 and is_delete = 0")
	 List<Comment> findByApplicationId(Long id);
}
