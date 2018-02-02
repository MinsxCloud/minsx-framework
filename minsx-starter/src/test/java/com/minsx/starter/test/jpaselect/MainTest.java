package com.minsx.starter.test.jpaselect;

import com.alibaba.fastjson.JSON;
import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.common.repository.auth.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Autowired
    MenuRepository menuRepository;


    @Test
    public void testSelect() {

        Criteria<Menu> c = new Criteria<Menu>();
        /*c.add(Restrictions.like("value","setting", true));*/
        c.add(Restrictions.eq("sort", 0, false));
        /*c.add(Restrictions.lte("submitTime", searchParam.getStartSubmitTime(), true));
        c.add(Restrictions.gte("submitTime", searchParam.getEndSubmitTime(), true));*/
        /*c.add(Restrictions.ne("sort", 0, true));*/
        /*c.add(Restrictions.in("value", Arrays.asList("/setting/menu","/monitor/resource"), true));*/
        List<Menu> menus = menuRepository.findAll(c);
        menus.forEach(menu->{
            System.out.println(JSON.toJSONString(menu));
        });


    }


}
