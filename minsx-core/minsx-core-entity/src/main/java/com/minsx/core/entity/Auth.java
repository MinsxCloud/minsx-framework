package com.minsx.core.entity;

import com.alibaba.fastjson.JSON;
import com.minsx.core.entity.base.SimpleMinsxEntity;

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
    @Column(nullable = false, name = "status")
    private Integer status;

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

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
