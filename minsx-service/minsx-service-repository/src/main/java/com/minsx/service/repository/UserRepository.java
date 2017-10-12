package com.minsx.service.repository;

import com.minsx.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
@Component
public interface UserRepository extends JpaRepository<User,Integer>{

}
