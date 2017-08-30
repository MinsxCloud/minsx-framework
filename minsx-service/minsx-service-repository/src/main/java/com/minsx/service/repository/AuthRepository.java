package com.minsx.service.repository;

import com.minsx.service.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AuthRepository
 * Created by Joker on 2017/8/30.
 */
public interface AuthRepository extends JpaRepository<Auth,Integer>{

}
