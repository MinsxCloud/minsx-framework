package com.minsx.core.system.service.implement;

import com.minsx.common.util.Node;
import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.common.entity.base.type.MenuClassifier;
import com.minsx.core.common.repository.auth.MenuRepository;
import com.minsx.core.system.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public ResponseEntity<?> getMenus(String name) {
        if (name == null) {
            Node<Menu> menuNode = menuListToNode(menuRepository.findAll());
            return new ResponseEntity<>(menuNode, HttpStatus.OK);
        } else {
            Menu menu = menuRepository.findByName(name);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getMenu(Integer menuId) {
        Menu menu = menuRepository.findOne(menuId);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTopMenus() {
        Node<Menu> menuNode = menuListToNode(menuRepository.findAllByClassifier(MenuClassifier.TOP.getValue()));
        return new ResponseEntity<>(menuNode, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getLeftMenus(Integer parentMenuId) {
        Node<Menu> node = menuListToNode(menuRepository.findAll(), parentMenuId);
        return new ResponseEntity<>(node, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveMenu(Menu menu) {
        Menu oldMenu = null;
        if (menu.getId() == null) {
            oldMenu = new Menu();
        } else {
            oldMenu = menuRepository.findOne(menu.getId());
            if (oldMenu == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        Menu tempMenu = menuRepository.findByName(menu.getName());
        if (tempMenu!=null&&tempMenu.getId()!=menu.getId()) {
            return new ResponseEntity<>("菜单系统名称已存在",HttpStatus.BAD_REQUEST);
        }
        oldMenu.setValue(menu.getValue());
        oldMenu.setSort(menu.getSort());
        oldMenu.setIcon(menu.getIcon());
        oldMenu.setParentMenuId(menu.getParentMenuId());
        oldMenu.setClassifier(menu.getClassifier());
        oldMenu.setDescription(menu.getDescription());
        oldMenu.setName(menu.getName());
        oldMenu.setAlias(menu.getAlias());
        oldMenu.setType(menu.getType());
        oldMenu.setState(menu.getState());
        menuRepository.save(oldMenu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteMenu(Integer id) {
        Menu menu = menuRepository.findOne(id);
        if (menu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Menu> menus = menuRepository.findAll();
            Node<Menu> menuNode = menuListToNode(menus, id);
            menus = menuNodeToList(menuNode);
            menus.add(menu);
            menuRepository.delete(menus);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------

    /**
     * menu list to node for all
     */
    public Node<Menu> menuListToNode(List<Menu> menus) {
        Map<Integer, List<Menu>> menuGroups = menus.stream().sorted(Comparator.comparing(Menu::getSort)).collect(groupingBy(Menu::getParentMenuId));
        Node<Menu> rootNode = new Node<Menu>();
        completeChilds(rootNode, menuGroups);
        return rootNode;
    }

    /**
     * menu list to node with parentMenuId
     */
    public Node<Menu> menuListToNode(List<Menu> menus, Integer parentMenuId) {
        Map<Integer, List<Menu>> menuGroups = menus.stream().sorted(Comparator.comparing(Menu::getSort)).collect(groupingBy(Menu::getParentMenuId));
        Node<Menu> rootNode = new Node<Menu>();
        completeChilds(rootNode, menuGroups);
        return getNodeByMenuId(rootNode, parentMenuId);
    }


    /**
     * menu groups to menu node
     */
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


    /**
     * Get specified node by menu id
     */
    private Node<Menu> getNodeByMenuId(Node<Menu> rootNode, Integer menuId) {
        Integer currentId = rootNode.getEntity() == null ? 0 : rootNode.getEntity().getId();
        if (currentId.equals(menuId)) {
            return rootNode;
        } else {
            if (rootNode.getChilds() == null) {
                return null;
            } else {
                for (Node<Menu> childNode : rootNode.getChilds()) {
                    Node<Menu> node = getNodeByMenuId(childNode, menuId);
                    if (node != null) {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    private List<Menu> menuNodeToList(Node<Menu> menuNode) {
        List<Menu> newMenuList = new ArrayList<>();
        completeMenuList(menuNode, newMenuList);
        return newMenuList;
    }

    private void completeMenuList(Node<Menu> menuNode, List<Menu> newMenuList) {
        if (menuNode.getChilds() != null) {
            menuNode.getChilds().forEach(child -> {
                newMenuList.add(child.getEntity());
                if (child.getChilds() != null) {
                    completeMenuList(child, newMenuList);
                }
            });
        }
    }

}
