package com.minsx.starter.test;

import com.alibaba.fastjson.JSON;
import com.minsx.common.util.Node;
import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.common.repository.auth.MenuRepository;
import com.minsx.starter.test.jpa.Query;
import com.minsx.starter.test.jpa.SingleSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {

    @Autowired
    MenuRepository menuRepository;


    @Test
    public void testSelect() {

        List<Menu> menus = menuRepository.findAll(new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicateA = builder.like(root.get("sort").as(String.class), "%" + "0" + "%");
                Predicate predicateB = builder.notEqual(root.get("parentMenuId").as(Integer.class),  0 );

                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(predicateA);
                predicates.add(predicateB);


                Predicate allPredicates = builder.and(predicates.toArray(new Predicate[predicates.size()]));
                query.where(allPredicates);

                return query.getRestriction();
            }
        });

        menus.forEach(menu -> {
            System.out.println(JSON.toJSONString(menu));
        });
    }


    @Test
    public void testB(){
        Query query = new Query("value","notEqual",3);
        List<Menu> menus = menuRepository.findAll(new SingleSpecification<Menu>(query));
        menus.forEach(menu -> {
            System.out.println(JSON.toJSONString(menu));
        });

    }


}
