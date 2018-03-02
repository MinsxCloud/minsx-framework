package com.minsx.core.common.repository.auth;

import com.minsx.core.common.entity.ordinary.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{



}
