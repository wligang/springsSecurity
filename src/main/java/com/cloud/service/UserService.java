package com.cloud.service;

import java.util.List;

import org.springframework.ui.Model;

import com.cloud.pojo.User;



public interface UserService {
	public List<User> findAll();

	public void save(User user);

}
