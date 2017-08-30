package com.minsx.service.entity;

import com.alibaba.fastjson.JSON;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User
 * Created by Joker on 2017/8/30.
 */
@Entity
@Table(name = "sys_users")
public class User  extends SimpleMinsxEntity implements Serializable {

    private static final long serialVersionUID = 7680851689006674668L;

    public User() {
    }

    public User(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setPassWord(user.getPassWord());
        this.setUserNick(user.getUserNick());
        this.setEmail(user.getEmail());
        this.setPhoneNum(user.getPhoneNum());
        this.setSignature(user.getSignature());
        this.setLastLoginTime(user.getLastLoginTime());
        this.setLocked(user.isLocked());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Integer id;

    @Column(nullable = false, name = "user_name", unique = true)
    private String userName;

    @Column(nullable = false, name = "pass_word")
    private String passWord;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles;

    @Column(name = "user_nick")
    private String userNick;

    @Column(name = "signature")
    private String signature;

    @Column(nullable = false, name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "phone_num", unique = true)
    private String phoneNum;

    @Email
    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "locked")
    private boolean locked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
