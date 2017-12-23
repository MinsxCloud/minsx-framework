package com.minsx.starter.test;

import java.time.LocalDateTime;

import com.minsx.core.entity.UserInfo;
import com.minsx.core.entity.auth.Menu;
import com.minsx.core.entity.type.*;
import com.minsx.core.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.minsx.core.entity.auth.Auth;
import com.minsx.core.entity.Role;
import com.minsx.core.entity.User;
import com.minsx.core.entity.Group;

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

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Test
    public void addAll() {
		addAuth();
		addRole();
		addGroup();
		addUserInfo();
		addUser();
	}
	
	@Test
    public void addAuth() {
		Auth auth = new Auth();
		auth.setType(AuthType.URL);
		auth.setValue("/user");
		auth.setCategory("用户页面权限");
		auth.setDescription("允许用户访问用户页面");
		auth.setCreateUserId(1);
		auth.setState(AuthState.ENABLE);
		authRepository.saveAndFlush(auth);
    }
	
	@Test
    public void addRole() {
		Role role = new Role();
		role.setAuths(authRepository.findAll());
		role.setCreateUserId(1);
		role.setName("admin");
		role.setAlias("管理员");
		role.setDiscription("拥有管理员相关权限的角色");
		role.setState(RoleState.ENABLE);
		roleRepository.saveAndFlush(role);
	}
	
	@Test
    public void addGroup() {
		Group userGroup = new Group();
		userGroup.setCreateUserId(1);
		userGroup.setName("manage");
		userGroup.setAlias("管理部门");
		userGroup.setDescription("用于管理某某项目的部门");
		userGroup.setParentId(0);
		userGroup.setRoles(roleRepository.findAll());
		userGroup.setState(UserGroupState.ENABLE);
		groupRepository.saveAndFlush(userGroup);
    }

    @Test
    public void addUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setCompany("开弦科技");
		userInfoRepository.save(userInfo);
    }

	@Test
    public void addUser() {
		User user = new User();
		user.setUserName("goodsave");
		user.setPassWord(passwordEncoder.encode("goodsave"));
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
		user.setUserInfo(userInfoRepository.findOne(1));
		userRepository.saveAndFlush(user);
    }

    @Test
	public void addMainMenu(){
		Menu menu = new Menu();
		menu.setName("contentManage");
		menu.setAlias("内容管理");
		menu.setCreateUserId(userRepository.findByUserName("goodsave").getId());
		menu.setDiscription("用于内容管理的菜单");
		menu.setParentMenuId(0);
		menu.setSort(2);
		menu.setState(MenuState.ENABLE);
		menu.setType(MenuType.NONE);
		menu.setValue(null);
		menuRepository.save(menu);
	}

    @Test
	public void addSubMenu(){
		Menu menu = new Menu();
		menu.setName("roleContentManage");
		menu.setAlias("角内管理");
		menu.setCreateUserId(userRepository.findByUserName("goodsave").getId());
		menu.setDiscription("用于角内管理的菜单");
		menu.setParentMenuId(menuRepository.findByName("roleManage").getId());
		menu.setSort(1);
		menu.setState(MenuState.ENABLE);
		menu.setType(MenuType.LINK);
		menu.setValue("/role/roleContent");
		menuRepository.save(menu);
	}




}
