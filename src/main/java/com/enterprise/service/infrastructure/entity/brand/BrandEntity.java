package com.enterprise.service.infrastructure.entity.brand;

import com.enterprise.service.infrastructure.entity.price.PriceEntity;
import com.enterprise.service.infrastructure.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "BRANDS")
public class BrandEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BRAND_ID")
    private short id;
    @Column(name = "NAME")
    private String name;


    @OneToMany
    @JoinColumn(name = "BRAND_ID")
    private List<ProductEntity> products;


    @OneToMany
    @JoinColumn(name = "PRICE_ID")
    private List<PriceEntity> prices;
}
