package com.cloud.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.mapper.UserMapper;
import com.cloud.pojo.SysRole;
import com.cloud.pojo.SysUser;
/**
 * 
 * @author liuze
 * 将用户权限 交给spring管理
 */
@Service
public class CustomUserService implements UserDetailsService{
    @Autowired
    public UserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userMapper.findByUserName(username);
		if(sysUser == null){
			throw new UsernameNotFoundException("用户名不存在");
		};
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		List<SysRole> sysRoleList = sysUser.getRoles();
		for (SysRole role : sysRoleList) {
			 authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
	}

}
