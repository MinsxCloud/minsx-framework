package com.minsx.core.common.entity.base.auth;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;
import com.minsx.core.common.entity.base.type.UrlState;
import com.minsx.core.common.entity.system.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 页面链接
 * Created by Joker on 2017/9/17.
 */
@Entity
@Table(name = "minsx_url")
public class Url extends SimpleMinsxEntity implements Serializable{

	private static final long serialVersionUID = 4116103521045949574L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "url_id")
    private Integer id;

    @Column(nullable = false, name = "value")
    private String value;

    @Column(nullable = false, name = "state")
    private Integer state;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public UrlState getState() {
		return UrlState.getUrlState(this.state);
	}

	public void setState(UrlState urlState) {
		this.state = urlState.getValue();
	}

	public void setState(Integer state) {
		this.state = state;
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
