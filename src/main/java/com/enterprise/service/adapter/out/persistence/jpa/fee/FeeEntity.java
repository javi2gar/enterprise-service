package com.enterprise.service.adapter.out.persistence.jpa.fee;

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
@Entity(name="FEES")
public class FeeEntity implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "FEE_ID")
        private Long id;
        private String name;
        private BigDecimal amount;

        @OneToMany
        @JoinColumn(name = "PRICE_LIST")
        private List<PriceEntity> prices;

}
