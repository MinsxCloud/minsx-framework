package com.minsx.core.system.service.api;

import com.minsx.core.common.entity.ordinary.User;
import org.springframework.http.ResponseEntity;

/**
 * UserService
 * Created by Joker on 2017/8/30.
 */
public interface UserService extends MinsxEntityService {

    ResponseEntity<?> getCurrentUserInfo();

    ResponseEntity<?> getUserInfo(Integer id);

    User getCurrentUser();

}
