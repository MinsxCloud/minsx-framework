package com.minsx.service.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Auth
 * Created by Joker on 2017/8/30.
 */
@Entity
@Table(name = "sys_auth")
public class Auth extends SimpleMinsxEntity implements Serializable {

    private static final long serialVersionUID = -1086790711293440255L;
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "auth_id")
    private Integer id;

    //权限类型(URL权限还是DATA权限)
    @Column(nullable = false, unique = true, name = "auth_type")
    private String type;

    //该权限能访问的URL
    @Column(nullable = false,unique = true, name = "auth_value")
    private String authValue;

    //权限类目(属于哪个类目eg:用户/文章....)
    @Column(nullable = false,unique = true, name = "auth_category")
    private String category;

    //描述
    @Column(nullable = true, name = "auth_description")
    private String description;

    //拥有该权限的角色
    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "auths")
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
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

   public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
