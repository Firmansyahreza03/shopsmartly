package com.rezalab.shopsmartly.model.base;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active")
    private Boolean active;

    @Version
    private Integer version;

    public Boolean getActive() {return active;}

    public void setActive(Boolean active) {active = active;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getCreatedBy() {return createdBy;}

    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public String getUpdatedBy() {return updatedBy;}

    public void setUpdatedBy(String updatedBy) {this.updatedBy = updatedBy;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}

    public Integer getVersion() {return version;}

    public void setVersion(Integer version) {this.version = version;}

    @PrePersist
    public void prePersist() {this.createdAt = LocalDateTime.now();}

    @PreUpdate
    public void preUpdate() {this.updatedAt = LocalDateTime.now();}
}
