package com.minsx.core.common.repository.system;

import com.minsx.core.common.entity.system.CustomSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSettingRepository extends JpaRepository<CustomSetting,Integer>{

}
