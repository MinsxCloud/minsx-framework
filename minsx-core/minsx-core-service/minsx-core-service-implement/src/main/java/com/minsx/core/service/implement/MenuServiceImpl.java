package com.minsx.core.service.implement;

import com.minsx.common.util.Node;
import com.minsx.core.entity.ordinary.auth.Menu;
import com.minsx.core.repository.MenuRepository;
import com.minsx.core.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuRepository menuRepository;

    @Override
    public ResponseEntity<?> getMenus() {
        Map<Integer, List<Menu>> menuGroups = menuRepository.findAll().stream().sorted(Comparator.comparing(Menu::getSort)).collect(groupingBy(Menu::getParentMenuId));
        Node<Menu> rootNode = new Node<Menu>();
        completeChilds(rootNode, menuGroups);
        return new ResponseEntity<>(rootNode, HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------
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
