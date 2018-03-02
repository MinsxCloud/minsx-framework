package com.minsx.core.system.service.implement;

import com.minsx.common.util.UserUtil;
import com.minsx.core.common.entity.ordinary.Auth;
import com.minsx.core.common.entity.ordinary.User;
import com.minsx.core.common.repository.system.UserRepository;
import com.minsx.core.system.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> getCurrentUserInfo() {
        User user = userRepository.findByUserName(UserUtil.getCurrentUserName());
        //UserUtil.getCurrentUserAuths().forEach(System.out::println);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserInfo(Integer id) {
        User user = userRepository.findOne(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Override
    public User getCurrentUser() {
        User user = null;
        String username = UserUtil.getCurrentUserName();
        if (username != null) {
            user = userRepository.findByUserName(username);
        }
        return user;
    }


    @Override
    public List<Auth> getCurrentUserAuths() {
        List<Auth> auths = new ArrayList<>();
        getCurrentUser().getGroups().forEach(group -> {
            group.getRoles().forEach(role -> {
                auths.addAll(role.getAuths());
            });
        });
        return auths;
    }


}
