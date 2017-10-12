package com.minsx.service.entity;

import com.alibaba.fastjson.JSON;
import com.minsx.service.base.SimpleMinsxEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 * Created by Joker on 2017/8/30.
 */
@Entity
@Table(name = "minsx_user")
public class User  extends SimpleMinsxEntity implements Serializable {

    private static final long serialVersionUID = 7680851689006674668L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Integer userId;

    @Column(nullable = false, name = "username", unique = true)
    private String userName;

    @Column(nullable = false, name = "password")
    private String passWord;

    @Column(name = "user_nick",unique = true)
    private String userNick;

    @Column(nullable = false, name = "status")
    private Integer status;

    @Column(name = "user_info_id",unique = true)
    private String userInfoId;

    @Column(name = "user_group_id",unique = true)
    private String userGroupId;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false,name = "phone", unique = true)
    private String phone;

    @Column(name = "face")
    private String face;

    @Column(name = "signature")
    private String signature;

    @Column(nullable = false, name = "register_time")
    private LocalDateTime registerTime;

    @Column(nullable = false, name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(nullable = false, name = "register_ip")
    private String registerIp;

    @Column(nullable = false, name = "last_login_ip")
    private String registerLoginIp;

    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    @Column(nullable = false, name = "edit_time")
    private LocalDateTime editTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterLoginIp() {
        return registerLoginIp;
    }

    public void setRegisterLoginIp(String registerLoginIp) {
        this.registerLoginIp = registerLoginIp;
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
