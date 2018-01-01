package com.minsx.core.common.repository.system;

import com.minsx.core.common.entity.system.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting,Integer>{

}
