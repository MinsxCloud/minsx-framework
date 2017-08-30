package com.minsx.service.repository;

import com.minsx.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
public interface UserRepository extends JpaRepository<User,Integer>{

}
