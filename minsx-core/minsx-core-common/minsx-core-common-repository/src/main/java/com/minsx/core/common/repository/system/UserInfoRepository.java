package com.minsx.core.common.repository.system;

import com.minsx.core.common.entity.ordinary.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{
	

}
