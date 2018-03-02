package com.minsx.core.system.service.implement;

import com.minsx.core.common.entity.ordinary.Role;
import com.minsx.core.common.repository.auth.RoleRepository;
import com.minsx.core.system.service.api.RoleService;
import com.minsx.core.system.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServerImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> getRoles(Pageable pageable) {
        Page<Role> roleList = roleRepository.findAll(pageable);
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveRoles(Role role) {
        Role oldRole = null;
        if (role.getId() == null) {
            oldRole = new Role();
        } else {
            oldRole = roleRepository.findOne(role.getId());
            if (oldRole == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        oldRole.setName(role.getName());
        oldRole.setState(role.getState());
        oldRole.setAlias(role.getAlias());
        oldRole.setDescription(role.getDescription());
        oldRole.setAuths(role.getAuths());
        oldRole.setCreateUserId(userService.getCurrentUser().getId());
        roleRepository.save(oldRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteRole(Integer id) {
        Role role =  roleRepository.findOne(id);
        if (role==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            roleRepository.delete(role);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
