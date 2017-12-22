package com.minsx.core.service.implement;

import com.minsx.common.util.UserUtil;
import com.minsx.core.entity.User;
import com.minsx.core.repository.UserRepository;
import com.minsx.core.service.api.UserService;
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
