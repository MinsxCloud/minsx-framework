package com.minsx.core.service.api;

import com.minsx.core.entity.User;
import org.springframework.http.ResponseEntity;

/**
 * UserService
 * Created by Joker on 2017/8/30.
 */
public interface UserService extends MinsxEntityService {

    ResponseEntity<?> getCurrentUserInfo();

    ResponseEntity<?> getUserInfo(Integer id);

}
