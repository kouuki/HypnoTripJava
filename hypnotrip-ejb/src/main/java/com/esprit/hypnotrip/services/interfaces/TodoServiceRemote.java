package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Todo;

@Remote
public interface TodoServiceRemote {
	void create(Todo todo);

	List<Todo> findAll();
}
