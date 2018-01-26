package com.minsx.core.common.repository.system;

import com.minsx.core.common.entity.developer.DevUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevUserRepository extends JpaRepository<DevUser,Integer>{

}
