package com.minsx.core.system.service.implement;

import com.minsx.core.common.entity.ordinary.Auth;
import com.minsx.core.common.repository.auth.AuthRepository;
import com.minsx.core.system.service.api.AuthService;
import com.minsx.core.system.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> getAuths(Pageable pageable) {
        Page<Auth> authList = authRepository.findAll(pageable);
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

    @Override
    public ResponseEntity<?> deleteAuth(Integer id) {
        Auth auth =  authRepository.findOne(id);
        if (auth==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            authRepository.delete(auth);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
