package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Todo;

@Local
public interface TodoServiceLocal {
	
	void create(Todo todo);
	List<Todo> findAll();

}
