package com.minsx.service.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Role
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
    @JoinTable(name = "sys_role_auth",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "auth_id"))
    public List<Auth> auths;


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

    public void addMenu(Auth menu) {
        this.auths.add(menu);
    }

    public void removeMenu(Auth menu) {
        if (this.auths.contains(menu)) {
            this.auths.remove(menu);
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
