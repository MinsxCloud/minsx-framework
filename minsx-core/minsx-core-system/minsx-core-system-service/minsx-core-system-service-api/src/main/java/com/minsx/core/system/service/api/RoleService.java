package com.minsx.core.system.service.api;

import com.minsx.core.common.entity.ordinary.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * RoleService
 * Created by Joker on 2017/8/30.
 */
public interface RoleService extends MinsxEntityService {

    ResponseEntity<?> getRoles(Pageable pageable);

    ResponseEntity<?> saveRoles(Role role);

    ResponseEntity<?> deleteRole(Integer id);


}
