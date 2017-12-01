package com.cloud.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.cloud.mapper.UserMapper;
import com.cloud.pojo.User;
import com.cloud.service.UserService;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl  implements UserService {
	@Autowired
	private JedisCluster jedisCluster;
	@Autowired
	private UserMapper userMapper;
	
	public List<User> findAll() {
//		String jList = jedisCluster.get("list");
//		if(StringUtils.isNotBlank(jList)){
//			List<User> uList = JSON.parseArray(jList,User.class);
//			return uList;
//		}
		List<User> list = userMapper.findAll();
//		jedisCluster.set("list", JSON.toJSONString(list));
		return list;
	}

	
	public void save(User user) {
		userMapper.save(user);
	}

}
