package com.minsx.core.common.repository.system;

import com.minsx.core.common.entity.system.CustomConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSettingRepository extends JpaRepository<CustomConfig,Integer>{

}
