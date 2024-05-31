package com.enterprise.service.application.port.in.read;

import com.enterprise.service.application.domain.Price;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface FindPriceUseCase {
    List<Price> findAllPricesByBrandIdAndProductIdAndRequestDate(@NotNull short brandId, Long productId, LocalDateTime requestDate);

}
