package com.minsx.core.entity.developer;

import com.minsx.core.entity.ordinary.base.SimpleMinsxEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class DeveloperKS extends SimpleMinsxEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "app_ks_id")
    private Integer id;

    @Length(min = 4, max = 64)
    @Column(nullable = false, name = "app_key", unique = true)
    private String appKey;

    @Length(min = 4, max = 64)
    @Column(nullable = false, name = "app_secret", unique = true)
    private String appSecret;

    @Column(nullable = false,name = "state")
    private Integer state;

    @Column(name = "discription")
    private String discription;

	@Column(nullable = false, name = "create_user_id")
    private Integer createUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
