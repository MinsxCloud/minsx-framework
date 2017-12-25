package com.minsx.core.common.repository.user;

import com.minsx.core.common.entity.system.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{
	

}
