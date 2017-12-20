package com.minsx.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.minsx.core.entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findById(Integer id);
	
	User findByUserName(String userName);

}
