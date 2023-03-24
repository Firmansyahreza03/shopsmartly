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
}
