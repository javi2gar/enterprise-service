package com.enterprise.service.adapter.out.persistence.jpa.price;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PriceEntityPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BRAND_ID")
    private short brandId;

    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    // Date range in which the indicated rate price
    @Id
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Id
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Id
    @Column(name = "PRICE_LIST")
    private Long feeId;

    @Id
    private int priority;
}
