package com.minsx.core.common.repository.auth;

import com.minsx.core.common.entity.ordinary.Auth;
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
