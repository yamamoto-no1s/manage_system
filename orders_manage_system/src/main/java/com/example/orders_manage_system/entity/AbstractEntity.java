package com.example.orders_manage_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
class AbstractEntity {
    @Column
    private Date modifiedAt;

    @Column(updatable = false)
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        setModifiedAt(new Date());
        setCreatedAt(new Date());
    }

    @PreUpdate
    public void onPreUpdate() {
        setModifiedAt(new Date());
    }
}
