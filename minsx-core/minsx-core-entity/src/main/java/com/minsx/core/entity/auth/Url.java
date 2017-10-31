package com.minsx.core.entity.auth;

import com.alibaba.fastjson.JSON;
import com.minsx.core.entity.base.SimpleMinsxEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    private Integer urlId;

    @Column(nullable = false, unique = true, name = "url_value")
    private String urlValue;

    @Column(nullable = false, unique = true, name = "status")
    private String status;

    @Column(nullable = false, unique = true, name = "description")
    private String description;

    //创建该权限的用户ID
    @Column(nullable = false, name = "create_user_id")
    private Integer createUserId;

    //创建时间
    @Column(nullable = false, name = "create_time")
    private LocalDateTime createTime;

    //编辑时间
    @Column(nullable = false, name = "edit_time")
    private LocalDateTime editTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
