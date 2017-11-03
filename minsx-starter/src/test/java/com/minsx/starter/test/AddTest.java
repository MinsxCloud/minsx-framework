package com.minsx.starter.test;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.minsx.core.entity.Auth;
import com.minsx.core.entity.Role;
import com.minsx.core.entity.User;
import com.minsx.core.entity.Group;
import com.minsx.core.entity.type.AuthState;
import com.minsx.core.entity.type.AuthType;
import com.minsx.core.entity.type.RoleState;
import com.minsx.core.entity.type.UserGroupState;
import com.minsx.core.entity.type.UserState;
import com.minsx.core.respository.AuthRepository;
import com.minsx.core.respository.RoleRepository;
import com.minsx.core.respository.GroupRepository;
import com.minsx.core.respository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Test
    public void addAll() {
		addAuth();
		addRole();
		addGroup();
		addUser();
	}
	
	@Test
    public void addAuth() {
		Auth auth = new Auth();
		auth.setAuthType(AuthType.URL);
		auth.setAuthValue("/user");
		auth.setCategory("用户页面权限");
		auth.setDescription("允许用户访问用户页面");
		auth.setCreateTime(LocalDateTime.now());
		auth.setCreateUserId(1);
		auth.setEditTime(LocalDateTime.now());
		auth.setState(AuthState.ENABLE);
		authRepository.saveAndFlush(auth);
    }
	
	@Test
    public void addRole() {
		Role role = new Role();
		role.setAuths(authRepository.findAll());
		role.setCreateTime(LocalDateTime.now());
		role.setCreateUserId(1);
		role.setEditTime(LocalDateTime.now());
		role.setName("admin");
		role.setAlias("管理员");
		role.setDiscription("拥有管理员相关权限的角色");
		role.setState(RoleState.ENABLE);
		roleRepository.saveAndFlush(role);
	}
	
	@Test
    public void addGroup() {
		Group userGroup = new Group();
		userGroup.setCreateTime(LocalDateTime.now());
		userGroup.setCreateUserId(1);
		userGroup.setEditTime(LocalDateTime.now());
		userGroup.setName("manage");
		userGroup.setAlias("管理部门");
		userGroup.setDescription("用于管理某某项目的部门");
		userGroup.setParentGroupId(0);
		userGroup.setRoles(roleRepository.findAll());
		userGroup.setState(UserGroupState.ENABLE);
		groupRepository.saveAndFlush(userGroup);
    }
	
	@Test
    public void addUser() {
		User user = new User();
		user.setUserName("goodsave");
		user.setPassWord(passwordEncoder.encode("goodsave"));
		user.setCreateTime(LocalDateTime.now());
		user.setEditTime(LocalDateTime.now());
		user.setEmail("goodsave@qq.com");
		user.setFace(null);
		user.setLastLoginTime(LocalDateTime.now());
		user.setPhone("15505090507");
		user.setRegisterIp("192.168.1.1");
		user.setLastLoginIp("192.168.1.1");
		user.setRegisterTime(LocalDateTime.now());
		user.setSignature("hi goodsave");
		user.setState(UserState.NORMAL);
		user.setUserNick("xiaochun");
		user.setGroups(groupRepository.findAll());
		userRepository.saveAndFlush(user);
    }
	

}
