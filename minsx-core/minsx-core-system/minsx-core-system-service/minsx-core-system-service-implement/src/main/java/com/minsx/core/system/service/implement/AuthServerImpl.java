package com.minsx.core.system.service.implement;

import com.minsx.common.util.UserUtil;
import com.minsx.core.common.entity.ordinary.Auth;
import com.minsx.core.common.repository.auth.AuthRepository;
import com.minsx.core.common.repository.system.UserRepository;
import com.minsx.core.system.service.api.AuthService;
import com.minsx.core.system.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServerImpl implements AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> getAuths() {
        List<Auth> authList = authRepository.findAll();
        return new ResponseEntity<>(authList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveAuths(Auth auth) {
        Auth oldAuth = null;
        if (auth.getId() == null) {
            oldAuth = new Auth();
        } else {
            oldAuth = authRepository.findOne(auth.getId());
            if (oldAuth == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        oldAuth.setCategory(auth.getCategory());
        oldAuth.setState(auth.getState());
        oldAuth.setType(auth.getType());
        oldAuth.setDescription(auth.getDescription());
        oldAuth.setValue(auth.getValue());
        oldAuth.setCreateUserId(userService.getCurrentUser().getId());
        authRepository.save(oldAuth);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
