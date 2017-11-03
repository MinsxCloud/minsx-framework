package com.minsx.core.entity.auth;

import com.alibaba.fastjson.JSON;
import com.minsx.core.entity.base.SimpleMinsxEntity;
import com.minsx.core.entity.type.MenuState;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单
 * Created by Joker on 2017/9/17.
 */
@Entity
@Table(name = "minsx_menu")
public class Menu extends SimpleMinsxEntity implements Serializable{

	private static final long serialVersionUID = 7418527767372866553L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "menu_id")
    private Integer menuId;

    @Column(nullable = false, name = "parent_menu_id")
    private Integer parentMenuId;

    @Column(nullable = false, unique = true, name = "name")
    private String name;
    
    @Column(nullable = false, unique = true, name = "alias")
    private String alias;

    @Column(nullable = false, unique = true, name = "state")
    private Integer state;

    @Column(nullable = false, unique = true, name = "url")
    private String url;

    @Column(nullable = false, unique = true, name = "type")
    private String type;
    
    @Column(nullable = false, unique = true, name = "discription")
    private String discription;

    //创建该权限的用户ID
    @Column(nullable = false, name = "create_user_id")
    private Integer createUserId;

    //创建时间
    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    //编辑时间
    @Column(nullable = false, name = "edit_time")
    private LocalDateTime editTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public MenuState getState() {
		return MenuState.getMenuState(this.state);
	}

	public void setState(MenuState menuState) {
		this.state = menuState.getValue();
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }
}
