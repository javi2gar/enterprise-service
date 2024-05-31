package com.enterprise.service.adapter.in.rest.price;

import com.enterprise.service.application.domain.Price;
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

