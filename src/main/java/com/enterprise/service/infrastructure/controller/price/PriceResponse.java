package com.enterprise.service.infrastructure.controller.price;

import com.enterprise.service.domain.model.Price;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class PriceResponse {
    @Size(max = 10000)
    List<Price> prices;
}

