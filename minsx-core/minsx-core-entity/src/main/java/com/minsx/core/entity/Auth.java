package com.minsx.core.entity;

import com.alibaba.fastjson.JSON;
import com.minsx.core.entity.base.SimpleMinsxEntity;
import com.minsx.core.entity.type.AuthState;
import com.minsx.core.entity.type.AuthType;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限
 * Created by Joker on 2017/8/30.
 */
@Entity
@Table(name = "minsx_auth")
public class Auth extends SimpleMinsxEntity implements Serializable {

    private static final long serialVersionUID = -1086790711293440255L;
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "auth_id")
    private Integer id;

    //权限类型(URL权限还是DATA权限)
    @Column(nullable = false, unique = true, name = "auth_type")
    private String authType;

    //该权限能访问的URL
    @Column(nullable = false, unique = true, name = "auth_value")
    private String authValue;

    //状态
    @Column(nullable = false, name = "state")
    private Integer state;

    //权限类目(属于哪个类目eg:用户/文章....)
    @Column(nullable = false, unique = true, name = "category")
    private String category;

    //描述
    @Column(nullable = true, name = "description")
    private String description;

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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthType getAuthType() {
        return AuthType.getAuthType(this.authType);
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType.getValue();
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }

    public AuthState getState() {
        return AuthState.getAuthState(this.state);
    }

    public void setState(AuthState authState) {
        this.state = authState.getValue();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
