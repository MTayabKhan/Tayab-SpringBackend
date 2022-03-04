package com.example.demo.service;

import java.util.List;

public interface HuntersServiceIF<T> {
	T create(T t);
	
	List<T> getAll();
	
	T getOne(Integer id);
	
	T replace(Integer id, T t);
	
	void remove(Integer id);
	
}
