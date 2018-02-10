package com.minsx.starter.test;

import com.minsx.core.common.entity.base.type.MenuType;
import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.common.repository.auth.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaStreamTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void testJpaStream() {

        TypedQuery<Menu> q = entityManager.createQuery("SELECT m FROM Menu m", Menu.class);
        Stream<Menu> menuStream = q.getResultList().stream();
        menuStream.filter(menu -> menu.getType().equals(MenuType.MENU)).forEach(menu -> System.out.println(menu.getAlias()));

    }

}
