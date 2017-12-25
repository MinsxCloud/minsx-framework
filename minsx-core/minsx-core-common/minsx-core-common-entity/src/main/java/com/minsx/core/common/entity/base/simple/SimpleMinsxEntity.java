package com.minsx.core.common.entity.base.simple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * SimpleMinsxEntity
 * Created by Joker on 2017/8/30.
 */
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createTime", "edit_time"})
public class SimpleMinsxEntity implements MinsxEntity {

    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @Column(name = "edit_time")
    private LocalDateTime editTime;

    protected LocalDateTime getCreatedAt() {
        return createTime;
    }

    protected LocalDateTime getUpdatedAt() {
        return editTime;
    }

    protected void setUpdatedAt(LocalDateTime updatedAt) {
        this.editTime = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        this.createTime = LocalDateTime.now();
        this.editTime = this.createTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.editTime = LocalDateTime.now();
    }

}
