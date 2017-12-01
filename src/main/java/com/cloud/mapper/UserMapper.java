package com.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cloud.pojo.SysUser;
import com.cloud.pojo.User;


@Mapper
public interface UserMapper {
	public List<User> findAll() ;

	public void save(User user);
	
	public SysUser findByUserName(String username);
}
