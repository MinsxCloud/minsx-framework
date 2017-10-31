package com.minsx.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsx.core.entity.Auth;


/**
 * AuthRepository
 * Created by Joker on 2017/8/30.
 */
public interface AuthRepository extends JpaRepository<Auth,Integer>{

}
