package com.minsx.core.common.repository.auth;

import com.minsx.core.common.entity.system.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * MenuRepository
 * Created by Joker on 2017/8/30.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>, PagingAndSortingRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    Menu findByName(String name);

    List<Menu> findAllByClassifier(String classifier);

}
