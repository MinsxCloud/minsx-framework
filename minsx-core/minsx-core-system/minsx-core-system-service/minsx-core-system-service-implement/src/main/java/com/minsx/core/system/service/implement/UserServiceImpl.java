package com.minsx.core.system.service.implement;

import com.minsx.common.util.UserUtil;
import com.minsx.core.common.entity.system.User;
import com.minsx.core.common.repository.system.UserRepository;
import com.minsx.core.system.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> getCurrentUserInfo() {
        User user = userRepository.findByUserName(UserUtil.getCurrentUserName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserInfo(Integer id) {
        User user = userRepository.findOne(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


}
