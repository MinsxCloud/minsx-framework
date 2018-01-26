package com.minsx.starter.test;

import com.minsx.core.common.entity.ordinary.Auth;
import com.minsx.core.common.entity.ordinary.Group;
import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.common.entity.ordinary.Role;
import com.minsx.core.common.entity.base.type.*;
import com.minsx.core.common.entity.developer.DevUser;
import com.minsx.core.common.entity.system.SystemConfig;
import com.minsx.core.common.entity.ordinary.User;
import com.minsx.core.common.entity.ordinary.UserInfo;
import com.minsx.core.common.repository.auth.AuthRepository;
import com.minsx.core.common.repository.auth.GroupRepository;
import com.minsx.core.common.repository.auth.MenuRepository;
import com.minsx.core.common.repository.auth.RoleRepository;
import com.minsx.core.common.repository.system.*;
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

    @Autowired
    SystemSettingRepository systemSettingRepository;

    @Autowired
    CustomSettingRepository customSettingRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void addAll() {
        addAuth();
        addRole();
        addGroup();
        addUser();
        addUserInfo();
        //addMenus();
        addDeveloper();
    }

    @Test
    public void addAuth() {
        Auth auth = new Auth();
        auth.setType(AuthType.URL);
        auth.setValue("/system");
        auth.setCategory("用户页面权限");
        auth.setDescription("允许用户访问用户页面");
        auth.setCreateUserId(userRepository.findByUserName("goodsave").getId());
        auth.setState(AuthState.ENABLE);
        authRepository.saveAndFlush(auth);
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setAuths(authRepository.findAll());
        role.setCreateUserId(userRepository.findByUserName("goodsave").getId());
        role.setName("admin");
        role.setAlias("管理员");
        role.setDescription("拥有管理员相关权限的角色");
        role.setState(RoleState.ENABLE);
        roleRepository.saveAndFlush(role);
    }

    @Test
    public void addGroup() {
        Group userGroup = new Group();
        userGroup.setCreateUserId(userRepository.findByUserName("goodsave").getId());
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
        userInfo.setCompany("米斯云科技有限公司");
        userInfo.setHomePage("https://github.com/MinsxCloud");
        userInfo.setUser(userRepository.findByUserName("goodsave"));
        userInfo.setBirthAddress("中国杭州");
        userInfo.setCurrentAddress("中国杭州");
        userInfo.setBirthday(LocalDateTime.now());
        userInfo.setEducation("大学");
        userInfo.setMarried(false);
        userInfo.setOccupation("中间件开发");
        userInfo.setMicroBlog("https://weibo.com/onlineJoker");
        userInfo.setQq(869304555L);
        userInfo.setSex("男");
        userInfo.setSchool("法琳斯顿");
        userInfo.setTelephone("15588699966");
        userInfo.setWeChat("goodsave");
        userInfoRepository.save(userInfo);
    }

    @Test
    public void addMenus() {
        List<String> names = Arrays.asList("index","systemManage", "menuManage", "authManage", "roleManage", "groupManage", "userManage", "accountManage", "integralManage", "contentManage", "classifyManage", "articleManage", "commentManage", "passManage", "emailManage");
        List<String> alias = Arrays.asList("系统首页","系统管理", "菜单管理", "权限管理", "角色管理", "分组管理", "用户管理", "帐号管理", "积分管理", "内容管理", "分类管理", "文章管理", "评论管理", "密码管理", "邮箱管理");
        List<String> descriptions = alias.stream().map(alia -> "用于" + alia).collect(Collectors.toList());
        List<Integer> parentMenuIds = Arrays.asList(0, 0, 2, 2, 2, 2, 0, 7, 8, 0, 10, 10, 10, 8, 7);
        List<Integer> sorts = Arrays.asList(0, 1, 2, 0, 1, 3, 2, 0, 1, 3, 0, 1, 2, 1, 0);
        List<MenuType> types = Arrays.asList(MenuType.LINK,MenuType.NONE, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.NONE, MenuType.NONE, MenuType.LINK, MenuType.NONE, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.LINK, MenuType.LINK);
        List<String> values = Arrays.asList("/",null, "/system/menu", "/system/auth", "/system/role", "/system/group", null, null, "/system/integral", null, "/content/classify", "/content/article", "/content/comment", "/content/pass", "/content/email");
        List<String> icons = Arrays.asList("el-icon-success", "el-icon-message", "el-icon-edit", "el-icon-loading", "el-icon-bell", "el-icon-mobile-phone", "el-icon-news", "el-icon-phone-outline", "el-icon-picture", "el-icon-rank", "el-icon-printer", "el-icon-star-on", "el-icon-share", "el-icon-service", "el-icon-sold-out");

        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Menu menu = new Menu();
            menu.setName(names.get(i));
            menu.setAlias(alias.get(i));
            menu.setCreateUserId(userRepository.findByUserName("goodsave").getId());
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


    @Test
    public void addSingleMenu() {
        Menu menu = new Menu();
        menu.setClassifier(MenuClassifier.LEFT);
        menu.setState(MenuState.ENABLE);
        menu.setName("mailSetting");
        menu.setAlias("邮件设置");
        menu.setDescription("邮件设置菜单");
        menu.setCreateUserId(userRepository.findByUserName("goodsave").getId());
        menu.setParentMenuId(7);
        menu.setIcon("el-icon-phone-outline");
        menu.setSort(1);
        menu.setType(MenuType.LINK);
        menu.setValue("/systemSetting/siteSetting/mailSetting");
        menuRepository.save(menu);
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

    //--------------------------------system config----------------------------------
    @Test
    public void addSystemConfig() {
        SystemConfig systemSetting = new SystemConfig();
        systemSetting.setConfigKey("siteName");
        systemSetting.setConfigValue("米斯云平台");
        systemSetting.setCreateUserId(userRepository.findByUserName("goodsave").getId());
        systemSetting.setDescription("站点名称，将显示在浏览器窗口标题等位置");
        systemSetting.setState(SystemSettingState.ENABLE);
        systemSetting.setType(SystemSettingType.SITE_INFO);
        systemSettingRepository.save(systemSetting);
    }



}
