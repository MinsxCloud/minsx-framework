package com.minsx.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsx.core.entity.ordinary.User;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findByUserName(String userName);

}
