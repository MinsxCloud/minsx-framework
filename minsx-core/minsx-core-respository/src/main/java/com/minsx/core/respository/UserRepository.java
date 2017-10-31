package com.minsx.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.minsx.core.entity.User;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
@Component
public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findByUserId(Integer userId);
	
	User findByUserName(String userName);

}
