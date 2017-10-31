package com.minsx.starter.test;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.minsx.core.entity.User;
import com.minsx.core.respository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	
	@Autowired
	UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
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
		user.setStatus(1);
		user.setUserNick("xiaochun");
		userRepository.save(user);
    }
	
	@Test
    public void updateUser() {
		User user = userRepository.findByUserName("goodsave");
		user.setPassWord(passwordEncoder.encode("goodsave"));
		userRepository.saveAndFlush(user);
    }

}
