package com.enterprise.service.adapter.out.persistence.jpa.product;

import com.enterprise.service.adapter.out.persistence.jpa.price.PriceEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@Entity(name = "PRODUCTS")
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String description;

    @Column(name = "PURCHASE_VALUE")
    private BigDecimal purchaseValue;

    @OneToMany
    @JoinColumn(name = "PRICE_ID")
    private List<PriceEntity> prices;

}


