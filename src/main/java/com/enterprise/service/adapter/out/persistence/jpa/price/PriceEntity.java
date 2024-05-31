package com.enterprise.service.adapter.out.persistence.jpa.price;

import com.enterprise.service.adapter.out.persistence.jpa.brand.BrandEntity;
import com.enterprise.service.adapter.out.persistence.jpa.fee.FeeEntity;
import com.enterprise.service.adapter.out.persistence.jpa.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@IdClass(PriceEntityPK.class)
@Entity(name = "PRICES")
public class PriceEntity {

    @Id
    @Column(name = "BRAND_ID")
    private short brandId;

    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    // Date range in which the indicated fee price
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Id
    @Column(name = "PRICE_LIST")
    private Long feeId;

    @Id
    private int priority;

    //  Pricing application disambiguate.
    //  If two fees coincide in a range of dates, the one with the highest priority (the highest numerical value) is applied.
    @Column(name = "PRICE")
    private BigDecimal price;

    private String curr;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", insertable = false, updatable = false)
    @MapsId("brandId")
    @ToString.Exclude
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    @MapsId("productId")
    @ToString.Exclude
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "PRICE_LIST", insertable = false, updatable = false)
    @MapsId("feeId")
    @ToString.Exclude
    private FeeEntity fee;
}