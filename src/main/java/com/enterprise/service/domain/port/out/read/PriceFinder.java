package com.enterprise.service.domain.port.out.read;

import com.enterprise.service.domain.model.Price;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface PriceFinder {
    List<Price> findAllPricesByBrandIdAndProductIdAndRequestDate(@NotNull short brandId, Long productId, LocalDateTime requestDate);

}
