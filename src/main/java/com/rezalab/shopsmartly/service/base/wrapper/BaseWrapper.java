package com.rezalab.shopsmartly.service.base.wrapper;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseWrapper implements Serializable {
    private static final long serialVersionUID = -6873422493435889366L;

    private Long id;
    private Long createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
    private Boolean active;
    private Integer version;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Long getCreatedBy() {return createdBy;}

    public void setCreatedBy(Long createdBy) {this.createdBy = createdBy;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public Long getUpdatedBy() {return updatedBy;}

    public void setUpdatedBy(Long updatedBy) {this.updatedBy = updatedBy;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}

    public Boolean getActive() {return active;}

    public void setActive(Boolean active) {this.active = active;}

    public Integer getVersion() {return version;}

    public void setVersion(Integer version) {this.version = version;}
}
