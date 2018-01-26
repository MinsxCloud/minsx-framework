package com.minsx.core.common.entity.ordinary;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;
import com.minsx.core.common.entity.base.type.GroupType;
import com.minsx.core.common.entity.base.type.UserGroupState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户分组
 * Created by Joker on 2017/9/17.
 */
@Entity
@Table(name = "minsx_group")
public class Group extends SimpleMinsxEntity implements Serializable {

	private static final long serialVersionUID = 7456879800037805192L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "group_id")
    private Integer id;

    @Column(nullable = false, name = "parent_group_id")
    private Integer parentId;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "alias")
    private String alias;

    @Column(nullable = false, name = "type")
    private String type;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "minsx_group_role",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles;

    @Column(nullable = false, name = "state")
    private Integer state;

    @Column(name = "description")
    private String description;

    //创建者ID
	@Column(name = "create_user_id")
    private Integer createUserId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

    public GroupType getType() {
        return GroupType.valueOf(type);
    }

    public void setType(GroupType groupType) {
        this.type = groupType.getValue();
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserGroupState getState() {
		return UserGroupState.getUserGroupState(this.state);
	}

	public void setState(UserGroupState userGroupState) {
		this.state = userGroupState.getValue();
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
}
