package com.minsx.core.common.entity.auth;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;
import com.minsx.core.common.entity.base.type.AuthState;
import com.minsx.core.common.entity.base.type.AuthType;
import com.minsx.core.common.entity.system.User;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(nullable = false, unique = true, name = "type")
    private String type;

    //该权限能访问的URL
    @Column(nullable = false, unique = true, name = "value")
    private String value;

    //状态
    @Column(nullable = false, name = "state")
    private Integer state;

    //权限类目(属于哪个类目eg:用户/文章....)
    @Column(nullable = false, unique = true, name = "category")
    private String category;

    //描述
    @Column(name = "description")
    private String description;

    //创建者ID
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User createUser;

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

    public AuthType getType() {
        return AuthType.getAuthType(this.type);
    }

    public void setType(AuthType type) {
        this.type = type.getValue();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AuthState getState() {
        return AuthState.getAuthState(this.state);
    }

    public void setState(AuthState authState) {
        this.state = authState.getValue();
    }

    public void setState(Integer state) {
        this.state = state;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }
}
