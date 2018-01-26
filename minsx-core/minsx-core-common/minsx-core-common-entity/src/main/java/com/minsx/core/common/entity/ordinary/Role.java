package com.minsx.core.common.entity.ordinary;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;
import com.minsx.core.common.entity.base.type.RoleState;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "alias")
    private String alias;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "minsx_role_auth",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "auth_id"))
    private List<Auth> auths;

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

    public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    public RoleState getState() {
		return RoleState.getRoleState(this.state);
	}

	public void setState(RoleState roleState) {
		this.state = roleState.getValue();
	}

	public void setState(Integer state) {
		this.state = state;
	}

    public void removeAuth(Auth auth) {
        if (this.auths!=null&&this.auths.contains(auth)) {
            this.auths.remove(auth);
        }
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
