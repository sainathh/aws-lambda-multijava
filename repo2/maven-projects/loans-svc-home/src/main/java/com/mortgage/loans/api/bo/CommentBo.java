package com.mortgage.loans.api.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.loans.api.dao.ICommentDao;
import com.mortgage.loans.api.models.Comment;
import com.mortgage.loans.api.utils.Constants;

@Service
public class CommentBo {

	@Autowired
	private ICommentDao commentDao;

	public Map<String, Object> save(Comment comment) {
		Comment c = new Comment();
		Map<String, Object> rsMap = new HashMap<>();

		try {
			if (comment.getId() == null) {
				comment.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
			}
			comment.setDelete(false);
			c = commentDao.save(comment);
			rsMap.put("comment", c);
			rsMap.put("message", Constants.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsMap;

	}

	public List<Comment> findAll() {
		List<Comment> list = new ArrayList<>();
		try {
			list = (List<Comment>) commentDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Comment findById(Long Id) {
		Comment c=new Comment();
		try {
			c=commentDao.findById(Id).orElse(null);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return c;
		
	}
	
	public Map<String, Object> remove(Long id) {
		Comment c=new Comment();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Comment cmnt = commentDao.findById(id).orElse(null);
			if (cmnt != null) {
				cmnt.setDelete(true);
				c = commentDao.save(cmnt);
				rsMap.put("message", c);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsMap;
	}
	
	public List<Comment> findByApplicationId(Long id) {
		List<Comment> list=new ArrayList<>();
		try {
			list= commentDao.findByApplicationId(id);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return list;
		
	}

}
