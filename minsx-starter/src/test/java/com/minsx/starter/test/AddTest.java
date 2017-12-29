package com.minsx.starter.test;

import com.minsx.core.common.entity.base.auth.Group;
import com.minsx.core.common.entity.base.auth.Role;
import com.minsx.core.common.entity.base.auth.Auth;
import com.minsx.core.common.entity.base.auth.Menu;
import com.minsx.core.common.entity.base.type.*;
import com.minsx.core.common.entity.developer.DevUser;
import com.minsx.core.common.entity.system.User;
import com.minsx.core.common.entity.system.UserInfo;
import com.minsx.core.common.repository.base.AuthRepository;
import com.minsx.core.common.repository.base.GroupRepository;
import com.minsx.core.common.repository.base.MenuRepository;
import com.minsx.core.common.repository.base.RoleRepository;
import com.minsx.core.common.repository.developer.DevUserRepository;
import com.minsx.core.common.repository.user.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    DevUserRepository devUserRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void addAll() {
        addAuth();
        addRole();
        addGroup();
        addUser();
        addUserInfo();
        addMainMenu();
        addDeveloper();
    }

    @Test
    public void addAuth() {
        Auth auth = new Auth();
        auth.setType(AuthType.URL);
        auth.setValue("/system");
        auth.setCategory("用户页面权限");
        auth.setDescription("允许用户访问用户页面");
        auth.setCreateUser(userRepository.findByUserName("goodsave"));
        auth.setState(AuthState.ENABLE);
        authRepository.saveAndFlush(auth);
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setAuths(authRepository.findAll());
        role.setCreateUser(userRepository.findByUserName("goodsave"));
        role.setName("admin");
        role.setAlias("管理员");
        role.setDescription("拥有管理员相关权限的角色");
        role.setState(RoleState.ENABLE);
        roleRepository.saveAndFlush(role);
    }

    @Test
    public void addGroup() {
        Group userGroup = new Group();
        userGroup.setCreateUser(userRepository.findByUserName("goodsave"));
        userGroup.setName("manage");
        userGroup.setAlias("管理部门");
        userGroup.setDescription("用于管理某某项目的部门");
        userGroup.setParentId(0);
        userGroup.setType(GroupType.SYSTEM);
        userGroup.setRoles(roleRepository.findAll());
        userGroup.setState(UserGroupState.ENABLE);
        groupRepository.saveAndFlush(userGroup);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUserName("goodsave");
        user.setPassWord(passwordEncoder.encode("goodsave"));
        user.setEmail("goodsave@qq.com");
        user.setFace(null);
        user.setLastLoginTime(LocalDateTime.now());
        user.setPhone("15505090507");
        user.setRegisterIp("192.168.1.1");
        user.setLastLoginIp("192.168.1.1");
        user.setRegisterTime(LocalDateTime.now());
        user.setSignature("hi goodsave");
        user.setState(UserState.NORMAL);
        user.setUserNick("xiaochun");
        user.setGroups(groupRepository.findAll());
        userRepository.saveAndFlush(user);
    }

    @Test
    public void addUserInfo() {
        /*用户详细信息*/
        UserInfo userInfo = new UserInfo();
        userInfo.setCompany("开弦科技");
        userInfo.setHomePage("https://github.com/MinsxFramework");
        userInfo.setUser(userRepository.findByUserName("goodsave"));
        userInfoRepository.save(userInfo);
    }

    @Test
    public void addMainMenu() {
        List<String> names = Arrays.asList("systemManage", "menuManage", "authManage", "roleManage", "groupManage", "userManage", "accountManage", "integralManage", "contentManage", "classifyManage", "articleManage", "commentManage", "passManage", "emailManage");
        List<String> alias = Arrays.asList("系统管理", "菜单管理", "权限管理", "角色管理", "分组管理", "用户管理", "帐号管理", "积分管理", "内容管理", "分类管理", "文章管理", "评论管理", "密码管理", "邮箱管理");
        List<String> descriptions = alias.stream().map(alia -> "用于" + alia).collect(Collectors.toList());
        List<Integer> parentMenuIds = Arrays.asList(0, 1, 1, 1, 1, 0, 6, 6, 0, 9, 9, 9, 7, 7);
        List<Integer> sorts = Arrays.asList(0, 2, 0, 1, 3, 1, 0, 1, 2, 0, 1, 2, 1, 0);
        List<MenuType> types = Arrays.asList(MenuType.NONE, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.NONE, MenuType.NONE, MenuType.LINK, MenuType.NONE, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.LINK);
        List<String> values = Arrays.asList(null, "/system/menu", "/system/auth", "/system/role", "/system/group", null, null, "/user/integral", null, "/content/classify", "/content/article", "/content/comment", "/content/pass", "/content/email");
        List<String> icons = Arrays.asList("el-icon-message", "el-icon-edit", "el-icon-loading", "el-icon-bell", "el-icon-mobile-phone", "el-icon-news", "el-icon-phone-outline", "el-icon-picture", "el-icon-rank", "el-icon-printer", "el-icon-star-on", "el-icon-share", "el-icon-service", "el-icon-sold-out");

        List<Menu> menus = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            Menu menu = new Menu();
            menu.setName(names.get(i));
            menu.setAlias(alias.get(i));
            menu.setCreateUser(userRepository.findByUserName("goodsave"));
            menu.setDescription(descriptions.get(i));
            menu.setParentMenuId(parentMenuIds.get(i));
            menu.setSort(sorts.get(i));
            menu.setState(MenuState.ENABLE);
            menu.setType(types.get(i));
            menu.setValue(values.get(i));
            menu.setIcon(icons.get(i));
            menus.add(menu);
        }
        menuRepository.save(menus);
    }
    //--------------------------------developer----------------------------------

    @Test
    public void addDeveloper() {
        DevUser devUser = new DevUser();
        devUser.setAccessKey("LTAIR93gYIIIbxB8");
        devUser.setAccessSecret(passwordEncoder.encode("qzWCZag9nFT96ifeTlfu6eBigNDkxN"));
        devUser.setState(DevUserState.NORMAL);
        devUser.setName("CCS帐号");
        devUser.setDescription("用于CCS接口调用");
        devUser.setUser(userRepository.findByUserName("goodsave"));
        devUserRepository.save(devUser);
    }


}
