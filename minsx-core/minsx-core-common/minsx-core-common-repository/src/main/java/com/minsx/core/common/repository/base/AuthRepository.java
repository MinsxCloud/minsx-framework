package com.minsx.core.common.repository.base;

import com.minsx.core.common.entity.base.auth.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * AuthRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface AuthRepository extends JpaRepository<Auth,Integer>{

    List<Auth> findAllByTypeOrderByIdAsc(String type);

}
