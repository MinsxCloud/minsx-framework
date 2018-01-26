package com.minsx.core.common.entity.system;

import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "minsx_app")
public class App extends SimpleMinsxEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "menu_id")
    private Integer id;

    @Column(nullable = false, unique = true, name = "name")
    private String name;

    @Column(nullable = false,name = "logo")
    private String logo;

    @Column(nullable = false, unique = true, name = "alias")
    private String alias;

    @Column(nullable = false,name = "state")
    private Integer state;

    @Column(nullable = false, unique = true, name = "client_id")
    private String clientId;

    @Column(nullable = false, unique = true, name = "client_secret")
    private String clientSecret;

    @Column(name = "description")
    private String description;

    //创建者ID
	@Column(name = "create_user_id")
    private Integer createUserId;


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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
