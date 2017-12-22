package com.minsx.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsx.core.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

}
