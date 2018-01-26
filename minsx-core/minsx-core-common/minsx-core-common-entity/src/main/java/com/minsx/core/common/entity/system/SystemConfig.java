package com.minsx.core.common.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minsx.core.common.entity.base.simple.SimpleMinsxEntity;
import com.minsx.core.common.entity.base.type.SystemSettingState;
import com.minsx.core.common.entity.base.type.SystemSettingType;
import com.minsx.core.common.entity.ordinary.User;

import javax.persistence.*;

@Entity
@Table(name = "minsx_system_config")
public class SystemConfig extends SimpleMinsxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "system_config_id")
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

    public SystemSettingState getState() {
        return SystemSettingState.getSystemSettingState(state);
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setState(SystemSettingState systemConfigState) {
        this.state = systemConfigState.getValue();
    }

    public SystemSettingType getType() {
        return SystemSettingType.getSystemSettingType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(SystemSettingType systemConfigType) {
        this.type = systemConfigType.getValue();
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
