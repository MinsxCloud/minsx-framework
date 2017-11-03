package com.minsx.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsx.core.entity.base.SimpleMinsxEntity;
import com.minsx.core.entity.type.UserState;

/**
 * 用户
 * Created by Joker on 2017/8/30.
 */
@Entity
@Table(name = "minsx_user")
public class User  extends SimpleMinsxEntity implements Serializable,UserDetails {

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

    @Column(nullable = false, name = "state")
    private Integer state;

    @Column(name = "user_info_id",unique = true)
    private String userInfoId;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "minsx_user_group",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "group_id"))
    private List<Group> groups;

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
    private String lastLoginIp;

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
    
    @Transient
	@JsonIgnore
	private Set<GrantedAuthority> authorities = null;
    
    public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.passWord;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.state!=-1;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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

	public UserState getState() {
		return UserState.getUserState(this.state);
	}

	public void setState(UserState userState) {
		this.state = userState.getValue();
	}

	public String getUserInfoId() {
		return userInfoId;
	}


	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
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


	public String getLastLoginIp() {
		return lastLoginIp;
	}


	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
    
}
