package com.minsx.core.entity;

import com.alibaba.fastjson.JSON;
import com.minsx.core.entity.base.SimpleMinsxEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色
 * Created by Joker on 2017/8/30.
 */
@Entity
@Table(name = "minsx_role")
public class Role  extends SimpleMinsxEntity implements Serializable {

    private static final long serialVersionUID = -2704877628383940871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "role_id")
    private Integer id;

    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @Column(nullable = false, name = "remark")
    private String remark;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "minsx_role_auth",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "auth_id"))
    private List<Auth> auths;

    @Column(nullable = false, name = "for_type")
    private String forType;

    @Column(nullable = false, name = "for_value")
    private String forValue;

    @Column(nullable = false, name = "status")
    private Integer status;

    @Column(nullable = false, name = "create_user_id")
    private Integer createUserId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    public String getForType() {
        return forType;
    }

    public void setForType(String forType) {
        this.forType = forType;
    }

    public String getForValue() {
        return forValue;
    }

    public void setForValue(String forValue) {
        this.forValue = forValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public void removeAuth(Auth auth) {
        if (this.auths.contains(auth)) {
            this.auths.remove(auth);
        }
    }
}
