package com.minsx.starter.test;

import com.alibaba.fastjson.JSON;
import com.minsx.common.util.Node;
import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.common.repository.auth.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@RunWith(SpringRunner.class)

@SpringBootTest
public class SelectTest {


    @Autowired
    MenuRepository menuRepository;

    @Test
    public void selectMenu() {
        Map<Integer, List<Menu>> menuGroups = menuRepository.findAll().stream().sorted(Comparator.comparing(Menu::getSort)).collect(groupingBy(Menu::getParentMenuId));
        Node<Menu> rootNode = new Node<Menu>();
        completeChilds(rootNode, menuGroups);
        System.out.println(JSON.toJSONString(rootNode));
    }

    private void completeChilds(Node<Menu> parentNode, Map<Integer, List<Menu>> menuGroups) {
        List<Menu> menus = menuGroups.get(parentNode.getEntity() == null ? 0 : parentNode.getEntity().getId());
        if (menus != null) {
            menus.forEach(menu -> {
                Node<Menu> childNode = new Node<Menu>(menu);
                completeChilds(childNode, menuGroups);
                parentNode.addChildNode(childNode);
            });
        }
    }


}
