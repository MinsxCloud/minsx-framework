package com.minsx.starter.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.minsx.core.common.entity.ordinary.User;
import com.minsx.core.common.repository.auth.AuthRepository;
import com.minsx.core.common.repository.auth.GroupRepository;
import com.minsx.core.common.repository.auth.RoleRepository;
import com.minsx.core.common.repository.system.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
	
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
    public void updateUser() {
		User user = userRepository.findByUserName("goodsave");
		user.setPassWord(passwordEncoder.encode("goodsave"));
		userRepository.saveAndFlush(user);
    }

}
