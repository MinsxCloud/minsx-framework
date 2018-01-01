package com.minsx.core.common.repository.auth;

import com.minsx.core.common.entity.auth.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * AuthRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>{

    Menu findByName(String name);

}
