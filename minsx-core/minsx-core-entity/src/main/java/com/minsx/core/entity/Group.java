package com.minsx.core.entity;

import com.alibaba.fastjson.JSON;
import com.minsx.core.entity.base.SimpleMinsxEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户分组
 * Created by Joker on 2017/9/17.
 */
@Entity
@Table(name = "minsx_user_group")
public class UserGroup extends SimpleMinsxEntity implements Serializable {
	
	private static final long serialVersionUID = 7456879800037805192L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_group_id")
    private Integer userGroupId;

    @Column(nullable = false, name = "parent_user_group_id", unique = true)
    private Integer parentUserGroupId;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @Column(nullable = false, name = "status", unique = true)
    private Integer status;

    @Column(nullable = false, name = "description", unique = true)
    private String description;

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


    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getParentUserGroupId() {
        return parentUserGroupId;
    }

    public void setParentUserGroupId(Integer parentUserGroupId) {
        this.parentUserGroupId = parentUserGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
