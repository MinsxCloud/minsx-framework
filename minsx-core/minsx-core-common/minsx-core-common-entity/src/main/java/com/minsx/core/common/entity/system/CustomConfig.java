package com.minsx.core.common.entity.system;

import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;
import com.minsx.core.common.entity.base.type.CustomSettingState;
import com.minsx.core.common.entity.base.type.CustomSettingType;

import javax.persistence.*;

@Entity
@Table(name = "minsx_custom_config")
public class CustomConfig extends SimpleMinsxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "custom_config_id")
    private Integer id;

    @Column(nullable = false, name = "config_key", unique = true)
    private String configKey;

    @Column(nullable = false, name = "config_value", unique = true)
    private String configValue;

    @Column(nullable = false,name = "state")
    private Integer state;

    @Column(nullable = false, name = "type")
    private String type;

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

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public CustomSettingState getState() {
        return CustomSettingState.getCustomSettingState(state);
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setState(CustomSettingState customConfigState) {
        this.state = customConfigState.getValue();
    }

    public CustomSettingType getType() {
        return CustomSettingType.getCustomSettingType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(CustomSettingType customConfigType) {
        this.type = customConfigType.getValue();
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
